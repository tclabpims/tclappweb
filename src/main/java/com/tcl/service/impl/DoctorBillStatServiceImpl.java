package com.tcl.service.impl;

import com.tcl.dao.*;
import com.tcl.model.*;
import com.tcl.service.DoctorBillStatService;
import com.tcl.service.PackageSaleStatService;
import com.tcl.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by LiuQi on 2017/9/3.
 */
@Service
public class DoctorBillStatServiceImpl implements DoctorBillStatService {

    @Autowired
    private DoctorBillStatModelMapper doctorBillStatDao;

    @Autowired
    private DoctorModelMapper doctorDao;

    @Autowired
    private OrderModelMapper orderDao;

    public List<DoctorBillStatModel> selectList(Map<String, Object> map) {
        return doctorBillStatDao.selectList(map);
    }

    public List<OrderModelWithBLOBs> orderList(Map<String, Object> map) {
        return orderDao.orderWithHospitalInfoList(map);
    }

    public List<DoctorModel> doctorList(Map<String, Object> map) {
        return doctorDao.selectByType(map);
    }

    public void cleanTable() {
        doctorBillStatDao.cleanTable();
    }

    public void statDoctorBillData() {
        //存放所有的开单医生
        List<DoctorModel> doctor_list = new ArrayList<DoctorModel>();
        for (DoctorModel doctorModel: (List<DoctorModel>)doctorList(new HashMap())) {
            //只统计开单医生,且医生的状态是可用
            if (doctorModel.getType() != null && doctorModel.getType().equals("1") &&
                    doctorModel.getStatus() != null && doctorModel.getStatus().equals("1")) doctor_list.add(doctorModel);
        }
        //存放所有的订单子项
        List<OrderModelWithBLOBs> order_list = orderDao.orderWithHospitalInfoList(new HashMap());
        //存放套餐销售的信息
        List<DoctorBillStatModel> doctorBillStat_list = new ArrayList<DoctorBillStatModel>();
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
                    DoctorBillStatModel doctorBillStatModel = new DoctorBillStatModel();
                    doctorBillStatModel.setYear(Integer.toString(i));
                    doctorBillStatModel.setMonth(Integer.toString(j));
                    doctorBillStatModel.setDoctorId(doctorModel.getId());
                    doctorBillStatModel.setDoctorName(doctorModel.getDoctorName());
                    doctorBillStat_list.add(doctorBillStatModel);
                }
            }
        }
        for (int i = 1; i <= now_month; i++) {
            for (DoctorModel doctorModel : doctor_list) {
                DoctorBillStatModel doctorBillStatModel = new DoctorBillStatModel();
                doctorBillStatModel.setYear(Integer.toString(now_year));
                doctorBillStatModel.setMonth(Integer.toString(i));
                doctorBillStatModel.setDoctorId(doctorModel.getId());
                doctorBillStatModel.setDoctorName(doctorModel.getDoctorName());
                doctorBillStat_list.add(doctorBillStatModel);
            }
        }
        List<DoctorBillStatModel> list = new ArrayList<DoctorBillStatModel>();
        for (DoctorBillStatModel doctorBillStatModel : doctorBillStat_list) {
            //当前年月
            String year_month = doctorBillStatModel.getYear() + "-" + doctorBillStatModel.getMonth();
            long bill_number = 0L;
            long bill_amount = 0L;
            for (OrderModelWithBLOBs orderModel : order_list) {
                String order_date = StringUtil.getFormatDate(orderModel.getCreateTime(), "yyyy-M");
                if (orderModel.getStatus() >= 1 && order_date.equals(year_month) && orderModel.getTradeModel() != null &&
                        orderModel.getTradeModel().getYzDoctorId() != null && !orderModel.getTradeModel().getYzDoctorId().trim().isEmpty() &&
                        doctorBillStatModel.getDoctorId() == Long.parseLong(orderModel.getTradeModel().getYzDoctorId()) &&
                        orderModel.getTradeModel().getYzDepartmentName() != null &&
                        doctorBillStatModel.getDoctorName().equals(orderModel.getTradeModel().getYzDepartmentName())) {
                    bill_number += orderModel.getPackageNum();
                    bill_amount += orderModel.getPrice() * orderModel.getPackageNum();
                }
            }
            doctorBillStatModel.setBillNum(bill_number);
            doctorBillStatModel.setBillAmount(bill_amount);
            doctorBillStatModel.setCreateTime(new Date());
            list.add(doctorBillStatModel);
        }
        cleanTable();
        doctorBillStatDao.batchInsert(list);
    }
}
