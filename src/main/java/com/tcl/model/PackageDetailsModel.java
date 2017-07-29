package com.tcl.model;

public class PackageDetailsModel {
    private Long id;

    private String hisId;

    private String hisName;

    private Long hisPrice;

    private Long packageId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHisId() {
        return hisId;
    }

    public void setHisId(String hisId) {
        this.hisId = hisId == null ? null : hisId.trim();
    }

    public String getHisName() {
        return hisName;
    }

    public void setHisName(String hisName) {
        this.hisName = hisName == null ? null : hisName.trim();
    }

    public Long getHisPrice() {
        return hisPrice;
    }

    public void setHisPrice(Long hisPrice) {
        this.hisPrice = hisPrice;
    }

    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }
}