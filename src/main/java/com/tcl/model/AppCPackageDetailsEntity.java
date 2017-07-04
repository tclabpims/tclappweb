package com.tcl.model;

import javax.persistence.*;

/**
 * Created by wang on 2017-07-03.
 */
@Entity
@Table(name = "app_c_package_details", schema = "", catalog = "tclapp")
public class AppCPackageDetailsEntity {
    private long id;
    private String hisId;
    private String hisName;
    private Long hisPrice;
    private long packageId;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "his_id")
    public String getHisId() {
        return hisId;
    }

    public void setHisId(String hisId) {
        this.hisId = hisId;
    }

    @Basic
    @Column(name = "his_name")
    public String getHisName() {
        return hisName;
    }

    public void setHisName(String hisName) {
        this.hisName = hisName;
    }

    @Basic
    @Column(name = "his_price")
    public Long getHisPrice() {
        return hisPrice;
    }

    public void setHisPrice(Long hisPrice) {
        this.hisPrice = hisPrice;
    }

    @Basic
    @Column(name = "package_id")
    public long getPackageId() {
        return packageId;
    }

    public void setPackageId(long packageId) {
        this.packageId = packageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppCPackageDetailsEntity that = (AppCPackageDetailsEntity) o;

        if (id != that.id) return false;
        if (packageId != that.packageId) return false;
        if (hisId != null ? !hisId.equals(that.hisId) : that.hisId != null) return false;
        if (hisName != null ? !hisName.equals(that.hisName) : that.hisName != null) return false;
        if (hisPrice != null ? !hisPrice.equals(that.hisPrice) : that.hisPrice != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (hisId != null ? hisId.hashCode() : 0);
        result = 31 * result + (hisName != null ? hisName.hashCode() : 0);
        result = 31 * result + (hisPrice != null ? hisPrice.hashCode() : 0);
        result = 31 * result + (int) (packageId ^ (packageId >>> 32));
        return result;
    }
}
