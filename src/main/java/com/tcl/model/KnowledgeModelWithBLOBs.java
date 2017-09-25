package com.tcl.model;

public class KnowledgeModelWithBLOBs extends KnowledgeModel {
    private String introduction;

    private String objective;

    private String textTime;

    private String needAttention;

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective == null ? null : objective.trim();
    }

    public String getTextTime() {
        return textTime;
    }

    public void setTextTime(String textTime) {
        this.textTime = textTime == null ? null : textTime.trim();
    }

    public String getNeedAttention() {
        return needAttention;
    }

    public void setNeedAttention(String needAttention) {
        this.needAttention = needAttention == null ? null : needAttention.trim();
    }
}