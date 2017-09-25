package com.tcl.model;

public class CollectManualModel {
    private Long id;

    private Long packageId;

    private String textMethod;

    private String collectTube;

    private String collectImg;

    private String storageCondit;

    private String collectRequire;

    private String needAttention;

    private String remark;

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

    public String getTextMethod() {
        return textMethod;
    }

    public void setTextMethod(String textMethod) {
        this.textMethod = textMethod == null ? null : textMethod.trim();
    }

    public String getCollectTube() {
        return collectTube;
    }

    public void setCollectTube(String collectTube) {
        this.collectTube = collectTube == null ? null : collectTube.trim();
    }

    public String getCollectImg() {
        return collectImg;
    }

    public void setCollectImg(String collectImg) {
        this.collectImg = collectImg == null ? null : collectImg.trim();
    }

    public String getStorageCondit() {
        return storageCondit;
    }

    public void setStorageCondit(String storageCondit) {
        this.storageCondit = storageCondit == null ? null : storageCondit.trim();
    }

    public String getCollectRequire() {
        return collectRequire;
    }

    public void setCollectRequire(String collectRequire) {
        this.collectRequire = collectRequire == null ? null : collectRequire.trim();
    }

    public String getNeedAttention() {
        return needAttention;
    }

    public void setNeedAttention(String needAttention) {
        this.needAttention = needAttention == null ? null : needAttention.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}