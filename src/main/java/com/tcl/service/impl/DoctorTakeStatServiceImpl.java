package com.tcl.service.impl;

import com.tcl.dao.DoctorBillStatModelMapper;
import com.tcl.dao.DoctorModelMapper;
import com.tcl.dao.DoctorTakeStatModelMapper;
import com.tcl.dao.OrderModelMapper;
import com.tcl.model.DoctorBillStatModel;
import com.tcl.model.DoctorModel;
import com.tcl.model.DoctorTakeStatModel;
import com.tcl.model.OrderModelWithBLOBs;
import com.tcl.service.DoctorBillStatService;
import com.tcl.service.DoctorTakeStatService;
import com.tcl.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by LiuQi on 2017/9/3.
 */
@Service
public class DoctorTakeStatServiceImpl implements DoctorTakeStatService {

    @Autowired
    private DoctorTakeStatModelMapper doctorTakeStatDao;

    @Autowired
    private DoctorModelMapper doctorDao;

    @Autowired
    private OrderModelMapper orderDao;

    public List<DoctorTakeStatModel> selectList(Map<String, Object> map) {
        return doctorTakeStatDao.selectList(map);
    }

    public List<OrderModelWithBLOBs> orderList(Map<String, Object> map) {
        return orderDao.orderWithHospitalInfoList(map);
    }

    public List<DoctorModel> doctorList(Map<String, Object> map) {
        return doctorDao.selectByType(map);
    }

    public void cleanTable() {
        doctorTakeStatDao.cleanTable();
    }

    public void statDoctorTakeData() {
        //存放所有的采集医生
        List<DoctorModel> doctor_list = new ArrayList<DoctorModel>();
        for (DoctorModel doctorModel: (List<DoctorModel>)doctorList(new HashMap())) {
            //只统计采集医生,且医生的状态是可用
            if (doctorModel.getType() != null && doctorModel.getType().equals("2") &&
                    doctorModel.getStatus() != null && doctorModel.getStatus().equals("1")) doctor_list.add(doctorModel);
        }
        //存放所有的订单子项
        List<OrderModelWithBLOBs> order_list = orderDao.orderWithHospitalInfoList(new HashMap());
        //存放套餐销售的信息
        List<DoctorTakeStatModel> doctorTakeStat_list = new ArrayList<DoctorTakeStatModel>();
        String date_now = StringUtil.getFormatDate(new Date(), "yyyy-MM");
        //今年
        int now_year = Integer.parseInt(date_now.substring(0, 4));
        //年数
        int year_num = Integer.parseInt(date_now.substring(0, 4)) - 2017;
        //当前月
        int now_month = Integer.parseInt(date_now.substring(5));
        for(int i = 2017; i<now_year; i++) {
            for (int j = 1; j < 13; j++) {
                for (DoctorModel doctorModel : doctor_list) {
                    DoctorTakeStatModel doctorTakeStatModel = new DoctorTakeStatModel();
                    doctorTakeStatModel.setYear(Integer.toString(i));
                    doctorTakeStatModel.setMonth(Integer.toString(j));
                    doctorTakeStatModel.setTakeDoctorId(doctorModel.getId());
                    doctorTakeStatModel.setTakeDoctorName(doctorModel.getDoctorName());
                    doctorTakeStat_list.add(doctorTakeStatModel);
                }
            }
        }
        for (int i = 1; i <= now_month; i++) {
            for (DoctorModel doctorModel : doctor_list) {
                DoctorTakeStatModel doctorTakeStatModel = new DoctorTakeStatModel();
                doctorTakeStatModel.setYear(Integer.toString(now_year));
                doctorTakeStatModel.setMonth(Integer.toString(i));
                doctorTakeStatModel.setTakeDoctorId(doctorModel.getId());
                doctorTakeStatModel.setTakeDoctorName(doctorModel.getDoctorName());
                doctorTakeStat_list.add(doctorTakeStatModel);
            }
        }
        List<DoctorTakeStatModel> list = new ArrayList<DoctorTakeStatModel>();
        for (DoctorTakeStatModel doctorTakeStatModel : doctorTakeStat_list) {
            //当前年月
            String year_month = doctorTakeStatModel.getYear() + "-" + doctorTakeStatModel.getMonth();
            long take_number = 0L;
            long take_amount = 0L;
            for (OrderModelWithBLOBs orderModel : order_list) {
                String order_date = StringUtil.getFormatDate(orderModel.getCreateTime(), "yyyy-M");
                if (orderModel.getStatus() >= 3 && order_date.equals(year_month) &&
                        orderModel.getTakeDoctorId() != null &&
                        doctorTakeStatModel.getTakeDoctorId() == orderModel.getTakeDoctorId() &&
                        orderModel.getDoctorModel() != null && doctorTakeStatModel.getTakeDoctorName().equals(orderModel.getDoctorModel().getDoctorName())) {
                    take_number += orderModel.getPackageNum();
                    take_amount += orderModel.getPrice() * orderModel.getPackageNum();
                }
            }
            doctorTakeStatModel.setTakeNum(take_number);
            doctorTakeStatModel.setTakeAmount(take_amount);
            doctorTakeStatModel.setCreateTime(new Date());
            list.add(doctorTakeStatModel);
        }
        cleanTable();
        doctorTakeStatDao.batchInsert(list);
    }
}
