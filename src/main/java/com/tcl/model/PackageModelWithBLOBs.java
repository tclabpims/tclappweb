package com.tcl.model;

public class PackageModelWithBLOBs extends PackageModel {
    private String needAttention;

    private String projectDesc;

    private String clause;

    public String getNeedAttention() {
        return needAttention;
    }

    public void setNeedAttention(String needAttention) {
        this.needAttention = needAttention == null ? null : needAttention.trim();
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc == null ? null : projectDesc.trim();
    }

    public String getClause() {
        return clause;
    }

    public void setClause(String clause) {
        this.clause = clause == null ? null : clause.trim();
    }
}