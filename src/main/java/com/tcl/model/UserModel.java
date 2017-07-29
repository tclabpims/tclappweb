package com.tcl.model;

import java.util.Date;

public class UserModel {
    private Long id;

    private String userName;

    private String passWord;

    private String name;

    private String sfzNum;

    private Date birthday;

    private String sex;

    private String address;

    private String status;

    private String verificationCode;

    private Date codeSendTime;

    private String touImg;

    private Date createTime;

    private Date modifyTime;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSfzNum() {
        return sfzNum;
    }

    public void setSfzNum(String sfzNum) {
        this.sfzNum = sfzNum == null ? null : sfzNum.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    public String getTouImg() {
        return touImg;
    }

    public void setTouImg(String touImg) {
        this.touImg = touImg == null ? null : touImg.trim();
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
}