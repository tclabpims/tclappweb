package com.tcl.model;

import java.util.Date;

public class PackageSaleStatModel {
    private Long id;

    private Long packageId;

    private String packageName;

    private Long packagePrice;

    private String year;

    private String month;

    private Long salesNum;

    private Long salesAmount;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        this.packageName = packageName == null ? null : packageName.trim();
    }

    public Long getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(Long packagePrice) {
        this.packagePrice = packagePrice;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month == null ? null : month.trim();
    }

    public Long getSalesNum() {
        return salesNum;
    }

    public void setSalesNum(Long salesNum) {
        this.salesNum = salesNum;
    }

    public Long getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(Long salesAmount) {
        this.salesAmount = salesAmount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}