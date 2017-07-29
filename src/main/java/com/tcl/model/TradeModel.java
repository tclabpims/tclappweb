package com.tcl.model;

import java.util.Date;

public class TradeModel {
    private Long id;

    private Long userId;

    private Long applicantId;

    private String tradeNum;

    private String status;

    private Date createTime;

    private Date payTime;

    private Date modifyTime;

    private Long price;

    private Integer num;

    private String name;

    private Long doctorId;

    private String doctorName;

    private Long hospitalId;

    private String hospitalName;

    private Integer payType;

    private Date orderedTime;

    private String yzDoctorId;

    private String yzDoctorNum;

    private String yzDoctorName;

    private String yzDepartmentNum;

    private String yzDepartmentName;

    private Date yzTime;

    private String createType;

    private Long relationId;

    private String doctorMsg;

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

    public Long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Long applicantId) {
        this.applicantId = applicantId;
    }

    public String getTradeNum() {
        return tradeNum;
    }

    public void setTradeNum(String tradeNum) {
        this.tradeNum = tradeNum == null ? null : tradeNum.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName == null ? null : doctorName.trim();
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

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Date getOrderedTime() {
        return orderedTime;
    }

    public void setOrderedTime(Date orderedTime) {
        this.orderedTime = orderedTime;
    }

    public String getYzDoctorId() {
        return yzDoctorId;
    }

    public void setYzDoctorId(String yzDoctorId) {
        this.yzDoctorId = yzDoctorId == null ? null : yzDoctorId.trim();
    }

    public String getYzDoctorNum() {
        return yzDoctorNum;
    }

    public void setYzDoctorNum(String yzDoctorNum) {
        this.yzDoctorNum = yzDoctorNum == null ? null : yzDoctorNum.trim();
    }

    public String getYzDoctorName() {
        return yzDoctorName;
    }

    public void setYzDoctorName(String yzDoctorName) {
        this.yzDoctorName = yzDoctorName == null ? null : yzDoctorName.trim();
    }

    public String getYzDepartmentNum() {
        return yzDepartmentNum;
    }

    public void setYzDepartmentNum(String yzDepartmentNum) {
        this.yzDepartmentNum = yzDepartmentNum == null ? null : yzDepartmentNum.trim();
    }

    public String getYzDepartmentName() {
        return yzDepartmentName;
    }

    public void setYzDepartmentName(String yzDepartmentName) {
        this.yzDepartmentName = yzDepartmentName == null ? null : yzDepartmentName.trim();
    }

    public Date getYzTime() {
        return yzTime;
    }

    public void setYzTime(Date yzTime) {
        this.yzTime = yzTime;
    }

    public String getCreateType() {
        return createType;
    }

    public void setCreateType(String createType) {
        this.createType = createType == null ? null : createType.trim();
    }

    public Long getRelationId() {
        return relationId;
    }

    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    public String getDoctorMsg() {
        return doctorMsg;
    }

    public void setDoctorMsg(String doctorMsg) {
        this.doctorMsg = doctorMsg == null ? null : doctorMsg.trim();
    }
}