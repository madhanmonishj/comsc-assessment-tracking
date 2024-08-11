package com.assessment.comsc.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ExternalForm implements Serializable {

    private Integer assessmentId;

    private Long userId;

    private Integer assessmentStatus;

    private String feedback;

    private Date feedbackDate;


}
