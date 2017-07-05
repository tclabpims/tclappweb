package com.tcl.model;

public class HospitalModelWithBLOBs extends HospitalModel {
    private String details;

    private String projectDesc;

    private String specialist;

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details == null ? null : details.trim();
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc == null ? null : projectDesc.trim();
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist == null ? null : specialist.trim();
    }
}