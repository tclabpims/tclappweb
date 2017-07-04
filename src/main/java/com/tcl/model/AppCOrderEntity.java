package com.tcl.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by wang on 2017-07-03.
 */
@Entity
@Table(name = "app_c_order", schema = "", catalog = "tclapp")
public class AppCOrderEntity {
    private long id;
    private long userId;
    private long tradeId;
    private long packageId;
    private String packageName;
    private Integer packageNum;
    private Long price;
    private Integer status;
    private Timestamp takeTime;
    private String reportTime;
    private String barcode;
    private String reportUrl;
    private String reportAcceptTime;
    private String remark;
    private Timestamp createTime;
    private Timestamp modifyTime;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "trade_id")
    public long getTradeId() {
        return tradeId;
    }

    public void setTradeId(long tradeId) {
        this.tradeId = tradeId;
    }

    @Basic
    @Column(name = "package_id")
    public long getPackageId() {
        return packageId;
    }

    public void setPackageId(long packageId) {
        this.packageId = packageId;
    }

    @Basic
    @Column(name = "package_name")
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Basic
    @Column(name = "package_num")
    public Integer getPackageNum() {
        return packageNum;
    }

    public void setPackageNum(Integer packageNum) {
        this.packageNum = packageNum;
    }

    @Basic
    @Column(name = "price")
    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "take_time")
    public Timestamp getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(Timestamp takeTime) {
        this.takeTime = takeTime;
    }

    @Basic
    @Column(name = "report_time")
    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    @Basic
    @Column(name = "barcode")
    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    @Basic
    @Column(name = "report_url")
    public String getReportUrl() {
        return reportUrl;
    }

    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
    }

    @Basic
    @Column(name = "report_accept_time")
    public String getReportAcceptTime() {
        return reportAcceptTime;
    }

    public void setReportAcceptTime(String reportAcceptTime) {
        this.reportAcceptTime = reportAcceptTime;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "modify_time")
    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppCOrderEntity that = (AppCOrderEntity) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (tradeId != that.tradeId) return false;
        if (packageId != that.packageId) return false;
        if (packageName != null ? !packageName.equals(that.packageName) : that.packageName != null) return false;
        if (packageNum != null ? !packageNum.equals(that.packageNum) : that.packageNum != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (takeTime != null ? !takeTime.equals(that.takeTime) : that.takeTime != null) return false;
        if (reportTime != null ? !reportTime.equals(that.reportTime) : that.reportTime != null) return false;
        if (barcode != null ? !barcode.equals(that.barcode) : that.barcode != null) return false;
        if (reportUrl != null ? !reportUrl.equals(that.reportUrl) : that.reportUrl != null) return false;
        if (reportAcceptTime != null ? !reportAcceptTime.equals(that.reportAcceptTime) : that.reportAcceptTime != null)
            return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (modifyTime != null ? !modifyTime.equals(that.modifyTime) : that.modifyTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (tradeId ^ (tradeId >>> 32));
        result = 31 * result + (int) (packageId ^ (packageId >>> 32));
        result = 31 * result + (packageName != null ? packageName.hashCode() : 0);
        result = 31 * result + (packageNum != null ? packageNum.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (takeTime != null ? takeTime.hashCode() : 0);
        result = 31 * result + (reportTime != null ? reportTime.hashCode() : 0);
        result = 31 * result + (barcode != null ? barcode.hashCode() : 0);
        result = 31 * result + (reportUrl != null ? reportUrl.hashCode() : 0);
        result = 31 * result + (reportAcceptTime != null ? reportAcceptTime.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (modifyTime != null ? modifyTime.hashCode() : 0);
        return result;
    }
}
