package com.tcl.model;

import java.util.Date;

public class DoctorModel {
    private Long id;

    private String userName;

    private String passWord;

    private String doctorName;

    private String sfzNum;

    private Long hospitalId;

    private String hospitalName;

    private String departmentNum;

    private String departmentName;

    private String cures;

    private String sex;

    private Integer age;

    private String title;

    private String position;

    private String touImg;

    private String zzImg;

    private String zzNum;

    private String zcImg;

    private String zcNum;

    private String education;

    private Integer readReportNum;

    private Integer diagnosisNum;

    private String status;

    private String type;

    private String verificationCode;

    private Date codeSendTime;

    private String isOpenAutoreceipt;

    private Integer receiptInterval;

    private Date lastLoginTime;

    private Date createTime;

    private Date modifyTime;

    private String auditReason;

    private String introduce;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord == null ? null : passWord.trim();
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName == null ? null : doctorName.trim();
    }

    public String getSfzNum() {
        return sfzNum;
    }

    public void setSfzNum(String sfzNum) {
        this.sfzNum = sfzNum == null ? null : sfzNum.trim();
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName == null ? null : hospitalName.trim();
    }

    public String getDepartmentNum() {
        return departmentNum;
    }

    public void setDepartmentNum(String departmentNum) {
        this.departmentNum = departmentNum == null ? null : departmentNum.trim();
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName == null ? null : departmentName.trim();
    }

    public String getCures() {
        return cures;
    }

    public void setCures(String cures) {
        this.cures = cures == null ? null : cures.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getTouImg() {
        return touImg;
    }

    public void setTouImg(String touImg) {
        this.touImg = touImg == null ? null : touImg.trim();
    }

    public String getZzImg() {
        return zzImg;
    }

    public void setZzImg(String zzImg) {
        this.zzImg = zzImg == null ? null : zzImg.trim();
    }

    public String getZzNum() {
        return zzNum;
    }

    public void setZzNum(String zzNum) {
        this.zzNum = zzNum == null ? null : zzNum.trim();
    }

    public String getZcImg() {
        return zcImg;
    }

    public void setZcImg(String zcImg) {
        this.zcImg = zcImg == null ? null : zcImg.trim();
    }

    public String getZcNum() {
        return zcNum;
    }

    public void setZcNum(String zcNum) {
        this.zcNum = zcNum == null ? null : zcNum.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public Integer getReadReportNum() {
        return readReportNum;
    }

    public void setReadReportNum(Integer readReportNum) {
        this.readReportNum = readReportNum;
    }

    public Integer getDiagnosisNum() {
        return diagnosisNum;
    }

    public void setDiagnosisNum(Integer diagnosisNum) {
        this.diagnosisNum = diagnosisNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode == null ? null : verificationCode.trim();
    }

    public Date getCodeSendTime() {
        return codeSendTime;
    }

    public void setCodeSendTime(Date codeSendTime) {
        this.codeSendTime = codeSendTime;
    }

    public String getIsOpenAutoreceipt() {
        return isOpenAutoreceipt;
    }

    public void setIsOpenAutoreceipt(String isOpenAutoreceipt) {
        this.isOpenAutoreceipt = isOpenAutoreceipt == null ? null : isOpenAutoreceipt.trim();
    }

    public Integer getReceiptInterval() {
        return receiptInterval;
    }

    public void setReceiptInterval(Integer receiptInterval) {
        this.receiptInterval = receiptInterval;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
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

    public String getAuditReason() {
        return auditReason;
    }

    public void setAuditReason(String auditReason) {
        this.auditReason = auditReason == null ? null : auditReason.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }
}