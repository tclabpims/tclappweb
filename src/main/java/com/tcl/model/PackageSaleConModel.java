package com.tcl.model;

/**
 * Created by LiuQi on 2017/8/29.
 * 套餐的销售情况
 */
public class PackageSaleConModel {
    //套餐编号
    private Long packageId;

    //套餐名称
    private String packageName;

    //销售总量
    private Long saleTotalNumber;

    //销售金额
    private Long saleSum;

    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Long getSaleTotalNumber() {
        return saleTotalNumber;
    }

    public void setSaleTotalNumber(Long saleTotalNumber) {
        this.saleTotalNumber = saleTotalNumber;
    }

    public Long getSaleSum() {
        return saleSum;
    }

    public void setSaleSum(Long saleSum) {
        this.saleSum = saleSum;
    }
}
