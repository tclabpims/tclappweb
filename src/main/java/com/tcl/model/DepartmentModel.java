package com.tcl.model;

import java.util.Date;

public class DepartmentModel {
    private Long id;

    private String departmentNum;

    private String departmentName;

    private Integer departmentNumber;

    private Date createTime;

    private Date modiftTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(Integer departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModiftTime() {
        return modiftTime;
    }

    public void setModiftTime(Date modiftTime) {
        this.modiftTime = modiftTime;
    }
}