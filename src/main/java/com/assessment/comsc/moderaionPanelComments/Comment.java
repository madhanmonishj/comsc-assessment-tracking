package com.assessment.comsc.moderaionPanelComments;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

// constructor class for comment
public class Comment {
    private Boolean isLinkClear;

    private Boolean isActivityClear;

    private Boolean isCriteriaClear;

    private Boolean isClassClear;

    private Boolean isWorkDone;

    private Boolean isMistakeFree;

    private Boolean isRequirementsClear;

    private Boolean isSubArrange;

    private Boolean isPenaltySub;

    private Boolean isFeedReturn;

    private Boolean isMarkingPlan;

    private Integer assessmentId;

    private String comments;

    private Boolean isScrutiny;

    private Boolean isModerated;

    private String dateCompleted;

    private Integer modPanId;
}
