package com.assessment.comsc.AvailabilityForm;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Form {
    private Boolean notified = true;
    private Boolean checked = true;
    private String date;
    private String times;
    private int stuNum;
    private String markDates;
    private String deadline;
    private String summary;
    private String assessmentID;
    private int avlID;

    public Form(){

    }

    public Boolean isIfNotified() {
        return notified;
    }

    public Boolean isIfChecked() {
        return checked;
    }

    public String getDate() {
        return date;
    }

    public String getTimes() {
        return times;
    }

    public int getStuNum() {
        return stuNum;
    }

    public String getMarkDates() {
        return markDates;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getSummary() {
        return summary;
    }

    public Integer getAssessmentID() {
        return Integer.valueOf(assessmentID);
    }

    public int getAvlID() {
        return avlID;
    }
}
