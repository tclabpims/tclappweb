package com.tcl.model;

import javax.persistence.*;

/**
 * Created by wang on 2017-07-03.
 */
@Entity
@Table(name = "app_c_hospital", schema = "", catalog = "tclapp")
public class AppCHospitalEntity {
    private long id;
    private String name;
    private String address;
    private String picUrl;
    private String telphone;
    private String details;
    private String projectDesc;
    private String route;
    private String specialist;
    private String alipayPayAccount;
    private String weixinPayAccount;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "pic_url")
    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Basic
    @Column(name = "telphone")
    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    @Basic
    @Column(name = "details")
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Basic
    @Column(name = "project_desc")
    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    @Basic
    @Column(name = "route")
    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    @Basic
    @Column(name = "specialist")
    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    @Basic
    @Column(name = "alipay_pay_account")
    public String getAlipayPayAccount() {
        return alipayPayAccount;
    }

    public void setAlipayPayAccount(String alipayPayAccount) {
        this.alipayPayAccount = alipayPayAccount;
    }

    @Basic
    @Column(name = "weixin_pay_account")
    public String getWeixinPayAccount() {
        return weixinPayAccount;
    }

    public void setWeixinPayAccount(String weixinPayAccount) {
        this.weixinPayAccount = weixinPayAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppCHospitalEntity that = (AppCHospitalEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (picUrl != null ? !picUrl.equals(that.picUrl) : that.picUrl != null) return false;
        if (telphone != null ? !telphone.equals(that.telphone) : that.telphone != null) return false;
        if (details != null ? !details.equals(that.details) : that.details != null) return false;
        if (projectDesc != null ? !projectDesc.equals(that.projectDesc) : that.projectDesc != null) return false;
        if (route != null ? !route.equals(that.route) : that.route != null) return false;
        if (specialist != null ? !specialist.equals(that.specialist) : that.specialist != null) return false;
        if (alipayPayAccount != null ? !alipayPayAccount.equals(that.alipayPayAccount) : that.alipayPayAccount != null)
            return false;
        if (weixinPayAccount != null ? !weixinPayAccount.equals(that.weixinPayAccount) : that.weixinPayAccount != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (picUrl != null ? picUrl.hashCode() : 0);
        result = 31 * result + (telphone != null ? telphone.hashCode() : 0);
        result = 31 * result + (details != null ? details.hashCode() : 0);
        result = 31 * result + (projectDesc != null ? projectDesc.hashCode() : 0);
        result = 31 * result + (route != null ? route.hashCode() : 0);
        result = 31 * result + (specialist != null ? specialist.hashCode() : 0);
        result = 31 * result + (alipayPayAccount != null ? alipayPayAccount.hashCode() : 0);
        result = 31 * result + (weixinPayAccount != null ? weixinPayAccount.hashCode() : 0);
        return result;
    }
}
