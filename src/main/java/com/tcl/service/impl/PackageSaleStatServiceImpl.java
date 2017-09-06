package com.tcl.service.impl;

import com.tcl.dao.*;
import com.tcl.model.*;
import com.tcl.service.HospitalSaleStatService;
import com.tcl.service.PackageSaleStatService;
import com.tcl.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by LiuQi on 2017/9/3.
 */
@Service
public class PackageSaleStatServiceImpl implements PackageSaleStatService {

    @Autowired
    private PackageSaleStatModelMapper packageSaleStatDao;

    @Autowired
    private PackageModelMapper packageDao;

    @Autowired
    private OrderModelMapper orderDao;

    public List<PackageSaleStatModel> selectList(Map<String, Object> map) {
        return packageSaleStatDao.selectList(map);
    }

    public List<OrderModelWithBLOBs> orderList(Map<String, Object> map) {
        return orderDao.orderWithHospitalInfoList(map);
    }

    public List<PackageModelWithBLOBs> packageList(Map<String, Object> map) {
        return packageDao.selectList(map);
    }

    public void cleanTable() {
        packageSaleStatDao.cleanTable();
    }

    public void statPackageSaleData() {
        //存放所有的套餐
        List<PackageModelWithBLOBs> package_list = packageList(new HashMap());
        //存放所有的订单子项
        List<OrderModelWithBLOBs> order_list = orderDao.orderWithHospitalInfoList(new HashMap());
        //存放套餐销售的信息
        List<PackageSaleStatModel> packageSaleStat_list = new ArrayList<PackageSaleStatModel>();
        String date_now = StringUtil.getFormatDate(new Date(), "yyyy-MM");
        //今年
        int now_year = Integer.parseInt(date_now.substring(0, 4));
        //年数
        int year_num = Integer.parseInt(date_now.substring(0, 4)) - 2017;
        //当前月
        int now_month = Integer.parseInt(date_now.substring(5));
        for(int i = 2017; i<now_year; i++) {
            for (int j = 1; j < 13; j++) {
                for (PackageModelWithBLOBs packageModel : package_list) {
                    PackageSaleStatModel packageSaleStatModel = new PackageSaleStatModel();
                    packageSaleStatModel.setYear(Integer.toString(i));
                    packageSaleStatModel.setMonth(Integer.toString(j));
                    packageSaleStatModel.setPackageId(packageModel.getId());
                    packageSaleStatModel.setPackageName(packageModel.getName());
                    packageSaleStat_list.add(packageSaleStatModel);
                }
            }
        }
        for (int i = 1; i <= now_month; i++) {
            for (PackageModelWithBLOBs packageModel : package_list) {
                PackageSaleStatModel packageSaleStatModel = new PackageSaleStatModel();
                packageSaleStatModel.setYear(Integer.toString(now_year));
                packageSaleStatModel.setMonth(Integer.toString(i));
                packageSaleStatModel.setPackageId(packageModel.getId());
                packageSaleStatModel.setPackageName(packageModel.getName());
                packageSaleStatModel.setPackagePrice(packageModel.getPrice());
                packageSaleStat_list.add(packageSaleStatModel);
            }
        }
        List<PackageSaleStatModel> list = new ArrayList<PackageSaleStatModel>();
        for (PackageSaleStatModel packageSaleStatModel : packageSaleStat_list) {
            //当前年月
            String year_month = packageSaleStatModel.getYear() + "-" + packageSaleStatModel.getMonth();
            long sale_number = 0L;
            long sale_amount = 0L;
            for (OrderModelWithBLOBs orderModel : order_list) {
                String order_date = StringUtil.getFormatDate(orderModel.getCreateTime(), "yyyy-M");
                if (orderModel.getStatus() >= 1 && order_date.equals(year_month) && orderModel.getTradeModel() != null &&
                        packageSaleStatModel.getPackageId() == orderModel.getPackageId() &&
                        packageSaleStatModel.getPackageName().equals(orderModel.getPackageName())) {
                    sale_number += orderModel.getPackageNum();
                    sale_amount += orderModel.getPrice() * orderModel.getPackageNum();
                }
            }
            packageSaleStatModel.setSalesNum(sale_number);
            packageSaleStatModel.setSalesAmount(sale_amount);
            packageSaleStatModel.setCreateTime(new Date());

            list.add(packageSaleStatModel);
        }
        cleanTable();
        packageSaleStatDao.batchInsert(list);
    }
}
