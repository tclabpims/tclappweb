package com.tcl.model;

public class HospitalModel {
    private Long id;

    private String name;

    private String address;

    private String picUrl;

    private String telphone;

    private String longitude;

    private String latitude;

    private String route;

    private String alipayPayAccount;

    private String weixinPayAccount;

    private String distance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route == null ? null : route.trim();
    }

    public String getAlipayPayAccount() {
        return alipayPayAccount;
    }

    public void setAlipayPayAccount(String alipayPayAccount) {
        this.alipayPayAccount = alipayPayAccount == null ? null : alipayPayAccount.trim();
    }

    public String getWeixinPayAccount() {
        return weixinPayAccount;
    }

    public void setWeixinPayAccount(String weixinPayAccount) {
        this.weixinPayAccount = weixinPayAccount == null ? null : weixinPayAccount.trim();
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance == null ? null : distance.trim();
    }
}