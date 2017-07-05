package com.tcl.model;

public class PackageModel {
    private Long id;

    private String name;

    private String useCrowd;

    private Long price;

    private String reportTime;

    private String wjCode;

    private Integer saleNum;

    private String picUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUseCrowd() {
        return useCrowd;
    }

    public void setUseCrowd(String useCrowd) {
        this.useCrowd = useCrowd == null ? null : useCrowd.trim();
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime == null ? null : reportTime.trim();
    }

    public String getWjCode() {
        return wjCode;
    }

    public void setWjCode(String wjCode) {
        this.wjCode = wjCode == null ? null : wjCode.trim();
    }

    public Integer getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }
}