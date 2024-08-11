package com.assessment.comsc.feedbackform;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PostFeedback implements Serializable {

    private Integer assessmentId;
    private Long userId;
    private Date scoreDate;
    private Date feedbackDate;
    private Double score;
    private String feedback;
    private Boolean assessmentMarksReleased;
    private Date dateAssessmentMarksReleased;
    private Boolean assessmentFeedbackReleased;
    private Date dateAssessmentFeedbackReleased;
    private Boolean assessmentMarksEntered;
    private Date dateAssessmentMarksEntered;
    private Boolean assessmentMarksTransferred;
    private Date dateAssessmentMarksTransferred;
    private Boolean assessmentFeedbackEntered;
    private Date dateAssessmentFeedbackEntered;
    private Boolean cohortFeedbackReturned;
    private Date dateCohortFeedbackReturned;
}

