package com.tcl.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by wang on 2017-07-03.
 */
@Entity
@javax.persistence.Table(name = "app_c_doctor", schema = "", catalog = "tclapp")
public class AppCDoctorEntity {
    private long id;

    @Id
    @javax.persistence.Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String userName;

    @Basic
    @javax.persistence.Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String passWord;

    @Basic
    @javax.persistence.Column(name = "pass_word")
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
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

    private String doctorName;

    @Basic
    @javax.persistence.Column(name = "doctor_name")
    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    private String sfzNum;

    @Basic
    @javax.persistence.Column(name = "sfz_num")
    public String getSfzNum() {
        return sfzNum;
    }

    public void setSfzNum(String sfzNum) {
        this.sfzNum = sfzNum;
    }

    private String sex;

    @Basic
    @javax.persistence.Column(name = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    private Integer age;

    @Basic
    @javax.persistence.Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    private String title;

    @Basic
    @javax.persistence.Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String introduce;

    @Basic
    @javax.persistence.Column(name = "introduce")
    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    private String touImg;

    @Basic
    @javax.persistence.Column(name = "tou_img")
    public String getTouImg() {
        return touImg;
    }

    public void setTouImg(String touImg) {
        this.touImg = touImg;
    }

    private String zzImg;

    @Basic
    @javax.persistence.Column(name = "zz_img")
    public String getZzImg() {
        return zzImg;
    }

    public void setZzImg(String zzImg) {
        this.zzImg = zzImg;
    }

    private Integer readReportNum;

    @Basic
    @javax.persistence.Column(name = "read_report_num")
    public Integer getReadReportNum() {
        return readReportNum;
    }

    public void setReadReportNum(Integer readReportNum) {
        this.readReportNum = readReportNum;
    }

    private Integer diagnosisNum;

    @Basic
    @javax.persistence.Column(name = "diagnosis_num")
    public Integer getDiagnosisNum() {
        return diagnosisNum;
    }

    public void setDiagnosisNum(Integer diagnosisNum) {
        this.diagnosisNum = diagnosisNum;
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

    private String type;

    @Basic
    @javax.persistence.Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String verificationCode;

    @Basic
    @javax.persistence.Column(name = "verification_code")
    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    private Timestamp codeSendTime;

    @Basic
    @javax.persistence.Column(name = "code_send_time")
    public Timestamp getCodeSendTime() {
        return codeSendTime;
    }

    public void setCodeSendTime(Timestamp codeSendTime) {
        this.codeSendTime = codeSendTime;
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

    private Timestamp modifyTime;

    @Basic
    @javax.persistence.Column(name = "modify_time")
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

        AppCDoctorEntity that = (AppCDoctorEntity) o;

        if (id != that.id) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (passWord != null ? !passWord.equals(that.passWord) : that.passWord != null) return false;
        if (hospitalId != null ? !hospitalId.equals(that.hospitalId) : that.hospitalId != null) return false;
        if (hospitalName != null ? !hospitalName.equals(that.hospitalName) : that.hospitalName != null) return false;
        if (doctorName != null ? !doctorName.equals(that.doctorName) : that.doctorName != null) return false;
        if (sfzNum != null ? !sfzNum.equals(that.sfzNum) : that.sfzNum != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (age != null ? !age.equals(that.age) : that.age != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (introduce != null ? !introduce.equals(that.introduce) : that.introduce != null) return false;
        if (touImg != null ? !touImg.equals(that.touImg) : that.touImg != null) return false;
        if (zzImg != null ? !zzImg.equals(that.zzImg) : that.zzImg != null) return false;
        if (readReportNum != null ? !readReportNum.equals(that.readReportNum) : that.readReportNum != null)
            return false;
        if (diagnosisNum != null ? !diagnosisNum.equals(that.diagnosisNum) : that.diagnosisNum != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (verificationCode != null ? !verificationCode.equals(that.verificationCode) : that.verificationCode != null)
            return false;
        if (codeSendTime != null ? !codeSendTime.equals(that.codeSendTime) : that.codeSendTime != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (modifyTime != null ? !modifyTime.equals(that.modifyTime) : that.modifyTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (passWord != null ? passWord.hashCode() : 0);
        result = 31 * result + (hospitalId != null ? hospitalId.hashCode() : 0);
        result = 31 * result + (hospitalName != null ? hospitalName.hashCode() : 0);
        result = 31 * result + (doctorName != null ? doctorName.hashCode() : 0);
        result = 31 * result + (sfzNum != null ? sfzNum.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (introduce != null ? introduce.hashCode() : 0);
        result = 31 * result + (touImg != null ? touImg.hashCode() : 0);
        result = 31 * result + (zzImg != null ? zzImg.hashCode() : 0);
        result = 31 * result + (readReportNum != null ? readReportNum.hashCode() : 0);
        result = 31 * result + (diagnosisNum != null ? diagnosisNum.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (verificationCode != null ? verificationCode.hashCode() : 0);
        result = 31 * result + (codeSendTime != null ? codeSendTime.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (modifyTime != null ? modifyTime.hashCode() : 0);
        return result;
    }
}
