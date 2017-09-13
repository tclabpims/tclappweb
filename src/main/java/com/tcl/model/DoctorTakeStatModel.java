package com.tcl.model;

import java.util.Date;

public class DoctorTakeStatModel {
    private Long id;

    private Long takeDoctorId;

    private String takeDoctorName;

    private String year;

    private String month;

    private Long takeNum;

    private Long takeAmount;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTakeDoctorId() {
        return takeDoctorId;
    }

    public void setTakeDoctorId(Long takeDoctorId) {
        this.takeDoctorId = takeDoctorId;
    }

    public String getTakeDoctorName() {
        return takeDoctorName;
    }

    public void setTakeDoctorName(String takeDoctorName) {
        this.takeDoctorName = takeDoctorName == null ? null : takeDoctorName.trim();
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

    public Long getTakeNum() {
        return takeNum;
    }

    public void setTakeNum(Long takeNum) {
        this.takeNum = takeNum;
    }

    public Long getTakeAmount() {
        return takeAmount;
    }

    public void setTakeAmount(Long takeAmount) {
        this.takeAmount = takeAmount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}