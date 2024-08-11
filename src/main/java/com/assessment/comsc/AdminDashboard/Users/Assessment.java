package com.assessment.comsc.AdminDashboard.Users;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents a assessment in the system.
 */
@Data
@AllArgsConstructor

public class Assessment {

    private String assessmentName;

    private Integer moduleLeaderId;

    private Integer panelStaffId;

    private Integer internalModeratorId;

    private Integer externalModeratorId;

    private String assessmentStatus;

    private String uploadedAssessmentName;

    private String moduleCode;

    private String moduleName;
}
