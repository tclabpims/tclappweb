package com.tcl.model;

public class OrderModelWithBLOBs extends OrderModel {
    private String remark;

    private String unscrambleContent;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getUnscrambleContent() {
        return unscrambleContent;
    }

    public void setUnscrambleContent(String unscrambleContent) {
        this.unscrambleContent = unscrambleContent == null ? null : unscrambleContent.trim();
    }
}