package com.tcl.model;

import java.util.Date;

public class OrderModel {
    private Long id;

    private Long userId;

    private Long tradeId;

    private Long packageId;

    private String packageName;

    private Integer packageNum;

    private Long price;

    private Integer status;

    private Date takeTime;

    private Long takeDoctorId;

    private String reportTime;

    private String reportTimeDesc;

    private String barcode;

    private String reportUrl;

    private String reportAcceptTime;

    private Date unscrambleTime;

    private String unscrambleAudioUrl;

    private String unscrambleAudioTime;

    private Date createTime;

    private Date modifyTime;

    private UserModel userModel;

    private TradeModel tradeModel;

    private DoctorModel doctorModel;

    private ApplicantModel applicantModel;

    private HospitalModelWithBLOBs hospitalModel;

    private PackageModel packageModel;

    public PackageModel getPackageModel() {
        return packageModel;
    }

    public void setPackageModel(PackageModel packageModel) {
        this.packageModel = packageModel;
    }

    public HospitalModelWithBLOBs getHospitalModel() {
        return hospitalModel;
    }

    public void setHospitalModel(HospitalModelWithBLOBs hospitalModel) {
        this.hospitalModel = hospitalModel;
    }

    public ApplicantModel getApplicantModel() {
        return applicantModel;
    }

    public void setApplicantModel(ApplicantModel applicantModel) {
        this.applicantModel = applicantModel;
    }

    public DoctorModel getDoctorModel() {
        return doctorModel;
    }

    public void setDoctorModel(DoctorModel doctorModel) {
        this.doctorModel = doctorModel;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public TradeModel getTradeModel() {
        return tradeModel;
    }

    public void setTradeModel(TradeModel tradeModel) {
        this.tradeModel = tradeModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTradeId() {
        return tradeId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
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

    public Integer getPackageNum() {
        return packageNum;
    }

    public void setPackageNum(Integer packageNum) {
        this.packageNum = packageNum;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(Date takeTime) {
        this.takeTime = takeTime;
    }

    public Long getTakeDoctorId() {
        return takeDoctorId;
    }

    public void setTakeDoctorId(Long takeDoctorId) {
        this.takeDoctorId = takeDoctorId;
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

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    public String getReportUrl() {
        return reportUrl;
    }

    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl == null ? null : reportUrl.trim();
    }

    public String getReportAcceptTime() {
        return reportAcceptTime;
    }

    public void setReportAcceptTime(String reportAcceptTime) {
        this.reportAcceptTime = reportAcceptTime == null ? null : reportAcceptTime.trim();
    }

    public Date getUnscrambleTime() {
        return unscrambleTime;
    }

    public void setUnscrambleTime(Date unscrambleTime) {
        this.unscrambleTime = unscrambleTime;
    }

    public String getUnscrambleAudioUrl() {
        return unscrambleAudioUrl;
    }

    public void setUnscrambleAudioUrl(String unscrambleAudioUrl) {
        this.unscrambleAudioUrl = unscrambleAudioUrl == null ? null : unscrambleAudioUrl.trim();
    }

    public String getUnscrambleAudioTime() {
        return unscrambleAudioTime;
    }

    public void setUnscrambleAudioTime(String unscrambleAudioTime) {
        this.unscrambleAudioTime = unscrambleAudioTime == null ? null : unscrambleAudioTime.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}