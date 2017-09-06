package com.tcl.service.impl;

import com.tcl.dao.HospitalModelMapper;
import com.tcl.dao.HospitalSaleStatModelMapper;
import com.tcl.dao.OrderModelMapper;
import com.tcl.model.*;
import com.tcl.service.HospitalSaleStatService;
import com.tcl.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by LiuQi on 2017/9/3.
 */
@Service
public class HospitalSaleStatServiceImpl implements HospitalSaleStatService {

    @Autowired
    private HospitalSaleStatModelMapper hospitalSaleStatDao;

    @Autowired
    private HospitalModelMapper hospitalDao;

    @Autowired
    private OrderModelMapper orderDao;

    public List<HospitalSaleStatModel> selectList(Map<String, Object> map) {
        return hospitalSaleStatDao.selectList(map);
    }

    public List<OrderModelWithBLOBs> orderList(Map<String, Object> map) {
        return orderDao.orderWithHospitalInfoList(map);
    }

    public List<HospitalModelWithBLOBs> hospitalList(Map<String, Object> map) {
        return hospitalDao.selectByType(map);
    }

    public void cleanTable() {
        hospitalSaleStatDao.cleanTable();
    }

    public void statHospitalSaleData() {
        //存放所有的医院
        List<HospitalModelWithBLOBs> hospital_list = hospitalDao.selectByType(new HashMap());
        //存放所有的订单子项
        List<OrderModelWithBLOBs> order_list = orderDao.orderWithHospitalInfoList(new HashMap());
        //存放医院销售信息
        List<HospitalSaleStatModel> hospitalSaleStat_list = new ArrayList<HospitalSaleStatModel>();
        String date_now = StringUtil.getFormatDate(new Date(), "yyyy-MM");
        //今年
        int now_year = Integer.parseInt(date_now.substring(0, 4));
        //年数
        int year_num = Integer.parseInt(date_now.substring(0, 4)) - 2017;
        //当前月
        int now_month = Integer.parseInt(date_now.substring(5));
        for(int i = 2017; i<now_year; i++) {
            for (int j = 1; j < 13; j++) {
                for (HospitalModelWithBLOBs hospitalModel : hospital_list) {
                    HospitalSaleStatModel hospitalSaleStatModel = new HospitalSaleStatModel();
                    hospitalSaleStatModel.setYear(Integer.toString(i));
                    hospitalSaleStatModel.setMonth(Integer.toString(j));
                    hospitalSaleStatModel.setHospitalId(hospitalModel.getId());
                    hospitalSaleStatModel.setHospitalName(hospitalModel.getName());
                    hospitalSaleStat_list.add(hospitalSaleStatModel);
                }
            }
        }
        for (int i = 1; i <= now_month; i++) {
            for (HospitalModelWithBLOBs hospitalModel : hospital_list) {
                HospitalSaleStatModel hospitalSaleStatModel = new HospitalSaleStatModel();
                hospitalSaleStatModel.setYear(Integer.toString(now_year));
                hospitalSaleStatModel.setMonth(Integer.toString(i));
                hospitalSaleStatModel.setHospitalId(hospitalModel.getId());
                hospitalSaleStatModel.setHospitalName(hospitalModel.getName());
                hospitalSaleStat_list.add(hospitalSaleStatModel);
            }
        }
        List<HospitalSaleStatModel> list = new ArrayList<HospitalSaleStatModel>();
        for (HospitalSaleStatModel hospitalSaleStatModel : hospitalSaleStat_list) {
            //当前年月
            String year_month = hospitalSaleStatModel.getYear() + "-" + hospitalSaleStatModel.getMonth();
            long sale_number = 0L;
            long sale_amount = 0L;
            for (OrderModelWithBLOBs orderModel : order_list) {
                String order_date = StringUtil.getFormatDate(orderModel.getCreateTime(), "yyyy-M");
                if (orderModel.getStatus() >= 1 && order_date.equals(year_month) && orderModel.getTradeModel() != null &&
                        hospitalSaleStatModel.getHospitalId() == orderModel.getTradeModel().getHospitalId() &&
                        hospitalSaleStatModel.getHospitalName().equals(orderModel.getTradeModel().getHospitalName())) {
                    sale_number += orderModel.getPackageNum();
                    sale_amount += orderModel.getPrice() * orderModel.getPackageNum();
                }
            }
            hospitalSaleStatModel.setSalesNum(sale_number);
            hospitalSaleStatModel.setSalesAmount(sale_amount);
            hospitalSaleStatModel.setCreateTime(new Date());

            list.add(hospitalSaleStatModel);
        }
        cleanTable();
        hospitalSaleStatDao.batchInsert(list);
    }
}
