package com.tcl.model;

/**
 * Created by LiuQi on 2017/8/29.
 */
public class HospitalFiManaModel {
    //医院编号
    Long id;

    //医院名称
    String hospitalName;

    //医院的订单量
    Long orderNumber;

    //销售额
    Long saleSum;

    //销售的套餐量
    Long totalPackages;

    public Long getTotalPackages() {
        return totalPackages;
    }

    public void setTotalPackages(Long totalPackages) {
        this.totalPackages = totalPackages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getSaleSum() {
        return saleSum;
    }

    public void setSaleSum(Long saleSum) {
        this.saleSum = saleSum;
    }
}
