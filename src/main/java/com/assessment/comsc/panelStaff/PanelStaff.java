package com.assessment.comsc.panelStaff;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PanelStaff {
    private Boolean considered;
    private String considerationDate;
    private String feedback;
    private Boolean notifiedModeratorSetter;
    private String notificationDate;
    private Boolean notifiedModuleLeader;
    private String moduleLeaderNotificationDate;
    private String assessmentStatus;
    private Integer assessmentId;

    public Boolean getConsidered() {
        return considered;
    }

    public String getConsiderationDate() {
        return considerationDate;
    }

    public String getFeedback() {
        return feedback;
    }

    public Boolean getNotifiedModeratorSetter() {
        return notifiedModeratorSetter;
    }

    public String getNotificationDate() {
        return notificationDate;
    }

    public Boolean getNotifiedModuleLeader() {
        return notifiedModuleLeader;
    }

    public String getModuleLeaderNotificationDate() {
        return moduleLeaderNotificationDate;
    }

    public String getAssessmentStatus() {
        return assessmentStatus;
    }

    public Integer getAssessmentId(){
        return assessmentId;
    }
}
