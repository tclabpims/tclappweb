package com.tcl.model;

public class PackageModel {
    private Long id;

    private String name;

    private String hisId;

    private String hisName;

    private String useCrowd;

    private Long price;

    private String reportTime;

    private String reportTimeDesc;

    private String wjCode;

    private Integer saleNum;

    private String picUrl;

    private String detailImg;

    private String status;

    private String sampleType;

    private String testType;

    private String diseaseType;

    private String takeType;

    public String getSampleType() {
        return sampleType;
    }

    public void setSampleType(String sampleType) {
        this.sampleType = sampleType == null ? null : sampleType.trim();
    }

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

    public String getReportTimeDesc() {
        return reportTimeDesc;
    }

    public void setReportTimeDesc(String reportTimeDesc) {
        this.reportTimeDesc = reportTimeDesc == null ? null : reportTimeDesc.trim();
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

    public String getDetailImg() {
        return detailImg;
    }

    public void setDetailImg(String detailImg) {
        this.detailImg = detailImg == null ? null : detailImg.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType == null ? null : testType.trim();
    }

    public String getDiseaseType() {
        return diseaseType;
    }

    public void setDiseaseType(String diseaseType) {
        this.diseaseType = diseaseType == null ? null : diseaseType.trim();
    }

    public String getTakeType() {
        return takeType;
    }

    public void setTakeType(String takeType) {
        this.takeType = takeType == null ? null : takeType.trim();
    }
}