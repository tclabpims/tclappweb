package com.tcl.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by wang on 2017-07-03.
 */
@Entity
@javax.persistence.Table(name = "app_c_trade", schema = "", catalog = "tclapp")
public class AppCTradeEntity {
    private long id;

    @Id
    @javax.persistence.Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private Long userId;

    @Basic
    @javax.persistence.Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    private Long applicantId;

    @Basic
    @javax.persistence.Column(name = "applicant_id")
    public Long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Long applicantId) {
        this.applicantId = applicantId;
    }

    private String tradeNum;

    @Basic
    @javax.persistence.Column(name = "trade_num")
    public String getTradeNum() {
        return tradeNum;
    }

    public void setTradeNum(String tradeNum) {
        this.tradeNum = tradeNum;
    }

    private String status;

    @Basic
    @javax.persistence.Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private Timestamp createTime;

    @Basic
    @javax.persistence.Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    private Timestamp payTime;

    @Basic
    @javax.persistence.Column(name = "pay_time")
    public Timestamp getPayTime() {
        return payTime;
    }

    public void setPayTime(Timestamp payTime) {
        this.payTime = payTime;
    }

    private Timestamp modifyTime;

    @Basic
    @javax.persistence.Column(name = "modify_time")
    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    private Long price;

    @Basic
    @javax.persistence.Column(name = "price")
    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    private Integer num;

    @Basic
    @javax.persistence.Column(name = "num")
    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    private String name;

    @Basic
    @javax.persistence.Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Long doctorId;

    @Basic
    @javax.persistence.Column(name = "doctor_id")
    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    private String doctorName;

    @Basic
    @javax.persistence.Column(name = "doctor_name")
    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    private Long hospitalId;

    @Basic
    @javax.persistence.Column(name = "hospital_id")
    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    private String hospitalName;

    @Basic
    @javax.persistence.Column(name = "hospital_name")
    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    private Integer payType;

    @Basic
    @javax.persistence.Column(name = "pay_type")
    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    private Timestamp orderedTime;

    @Basic
    @javax.persistence.Column(name = "ordered_time")
    public Timestamp getOrderedTime() {
        return orderedTime;
    }

    public void setOrderedTime(Timestamp orderedTime) {
        this.orderedTime = orderedTime;
    }

    private String yzDoctorId;

    @Basic
    @javax.persistence.Column(name = "yz_doctor_id")
    public String getYzDoctorId() {
        return yzDoctorId;
    }

    public void setYzDoctorId(String yzDoctorId) {
        this.yzDoctorId = yzDoctorId;
    }

    private String yzDoctorNum;

    @Basic
    @javax.persistence.Column(name = "yz_doctor_num")
    public String getYzDoctorNum() {
        return yzDoctorNum;
    }

    public void setYzDoctorNum(String yzDoctorNum) {
        this.yzDoctorNum = yzDoctorNum;
    }

    private String yzDoctorName;

    @Basic
    @javax.persistence.Column(name = "yz_doctor_name")
    public String getYzDoctorName() {
        return yzDoctorName;
    }

    public void setYzDoctorName(String yzDoctorName) {
        this.yzDoctorName = yzDoctorName;
    }

    private String yzDepartmentNum;

    @Basic
    @javax.persistence.Column(name = "yz_department_num")
    public String getYzDepartmentNum() {
        return yzDepartmentNum;
    }

    public void setYzDepartmentNum(String yzDepartmentNum) {
        this.yzDepartmentNum = yzDepartmentNum;
    }

    private String yzDepartmentName;

    @Basic
    @javax.persistence.Column(name = "yz_department_name")
    public String getYzDepartmentName() {
        return yzDepartmentName;
    }

    public void setYzDepartmentName(String yzDepartmentName) {
        this.yzDepartmentName = yzDepartmentName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppCTradeEntity that = (AppCTradeEntity) o;

        if (id != that.id) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (applicantId != null ? !applicantId.equals(that.applicantId) : that.applicantId != null) return false;
        if (tradeNum != null ? !tradeNum.equals(that.tradeNum) : that.tradeNum != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (payTime != null ? !payTime.equals(that.payTime) : that.payTime != null) return false;
        if (modifyTime != null ? !modifyTime.equals(that.modifyTime) : that.modifyTime != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (num != null ? !num.equals(that.num) : that.num != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (doctorId != null ? !doctorId.equals(that.doctorId) : that.doctorId != null) return false;
        if (doctorName != null ? !doctorName.equals(that.doctorName) : that.doctorName != null) return false;
        if (hospitalId != null ? !hospitalId.equals(that.hospitalId) : that.hospitalId != null) return false;
        if (hospitalName != null ? !hospitalName.equals(that.hospitalName) : that.hospitalName != null) return false;
        if (payType != null ? !payType.equals(that.payType) : that.payType != null) return false;
        if (orderedTime != null ? !orderedTime.equals(that.orderedTime) : that.orderedTime != null) return false;
        if (yzDoctorId != null ? !yzDoctorId.equals(that.yzDoctorId) : that.yzDoctorId != null) return false;
        if (yzDoctorNum != null ? !yzDoctorNum.equals(that.yzDoctorNum) : that.yzDoctorNum != null) return false;
        if (yzDoctorName != null ? !yzDoctorName.equals(that.yzDoctorName) : that.yzDoctorName != null) return false;
        if (yzDepartmentNum != null ? !yzDepartmentNum.equals(that.yzDepartmentNum) : that.yzDepartmentNum != null)
            return false;
        if (yzDepartmentName != null ? !yzDepartmentName.equals(that.yzDepartmentName) : that.yzDepartmentName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (applicantId != null ? applicantId.hashCode() : 0);
        result = 31 * result + (tradeNum != null ? tradeNum.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (payTime != null ? payTime.hashCode() : 0);
        result = 31 * result + (modifyTime != null ? modifyTime.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (num != null ? num.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (doctorId != null ? doctorId.hashCode() : 0);
        result = 31 * result + (doctorName != null ? doctorName.hashCode() : 0);
        result = 31 * result + (hospitalId != null ? hospitalId.hashCode() : 0);
        result = 31 * result + (hospitalName != null ? hospitalName.hashCode() : 0);
        result = 31 * result + (payType != null ? payType.hashCode() : 0);
        result = 31 * result + (orderedTime != null ? orderedTime.hashCode() : 0);
        result = 31 * result + (yzDoctorId != null ? yzDoctorId.hashCode() : 0);
        result = 31 * result + (yzDoctorNum != null ? yzDoctorNum.hashCode() : 0);
        result = 31 * result + (yzDoctorName != null ? yzDoctorName.hashCode() : 0);
        result = 31 * result + (yzDepartmentNum != null ? yzDepartmentNum.hashCode() : 0);
        result = 31 * result + (yzDepartmentName != null ? yzDepartmentName.hashCode() : 0);
        return result;
    }
}
