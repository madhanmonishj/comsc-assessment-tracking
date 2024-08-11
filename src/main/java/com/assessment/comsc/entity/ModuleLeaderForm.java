package com.assessment.comsc.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ModuleLeaderForm implements Serializable {
    private Integer userId;

    private String feedback;

    private Boolean editsMade;

    private Date completedDate;

    private Boolean panelNotify;

    private Date notifyDate;

    private Integer assessmentId;
}
