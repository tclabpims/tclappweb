package com.tcl.model;

import javax.persistence.*;

/**
 * Created by wang on 2017-07-03.
 */
@Entity
@Table(name = "app_c_package", schema = "", catalog = "tclapp")
public class AppCPackageEntity {
    private long id;
    private String name;
    private String useCrowd;
    private Long price;
    private String needAttention;
    private String projectDesc;
    private String reportTime;
    private String clause;
    private String wjCode;
    private Integer saleNum;
    private String picUrl;

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
    @Column(name = "use_crowd")
    public String getUseCrowd() {
        return useCrowd;
    }

    public void setUseCrowd(String useCrowd) {
        this.useCrowd = useCrowd;
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
    @Column(name = "need_attention")
    public String getNeedAttention() {
        return needAttention;
    }

    public void setNeedAttention(String needAttention) {
        this.needAttention = needAttention;
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
    @Column(name = "report_time")
    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    @Basic
    @Column(name = "clause")
    public String getClause() {
        return clause;
    }

    public void setClause(String clause) {
        this.clause = clause;
    }

    @Basic
    @Column(name = "wj_code")
    public String getWjCode() {
        return wjCode;
    }

    public void setWjCode(String wjCode) {
        this.wjCode = wjCode;
    }

    @Basic
    @Column(name = "sale_num")
    public Integer getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    @Basic
    @Column(name = "pic_url")
    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppCPackageEntity that = (AppCPackageEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (useCrowd != null ? !useCrowd.equals(that.useCrowd) : that.useCrowd != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (needAttention != null ? !needAttention.equals(that.needAttention) : that.needAttention != null)
            return false;
        if (projectDesc != null ? !projectDesc.equals(that.projectDesc) : that.projectDesc != null) return false;
        if (reportTime != null ? !reportTime.equals(that.reportTime) : that.reportTime != null) return false;
        if (clause != null ? !clause.equals(that.clause) : that.clause != null) return false;
        if (wjCode != null ? !wjCode.equals(that.wjCode) : that.wjCode != null) return false;
        if (saleNum != null ? !saleNum.equals(that.saleNum) : that.saleNum != null) return false;
        if (picUrl != null ? !picUrl.equals(that.picUrl) : that.picUrl != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (useCrowd != null ? useCrowd.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (needAttention != null ? needAttention.hashCode() : 0);
        result = 31 * result + (projectDesc != null ? projectDesc.hashCode() : 0);
        result = 31 * result + (reportTime != null ? reportTime.hashCode() : 0);
        result = 31 * result + (clause != null ? clause.hashCode() : 0);
        result = 31 * result + (wjCode != null ? wjCode.hashCode() : 0);
        result = 31 * result + (saleNum != null ? saleNum.hashCode() : 0);
        result = 31 * result + (picUrl != null ? picUrl.hashCode() : 0);
        return result;
    }
}
