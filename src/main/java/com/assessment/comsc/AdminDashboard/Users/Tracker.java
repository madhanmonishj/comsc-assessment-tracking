package com.assessment.comsc.AdminDashboard.Users;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents an assesssment in the system.
 */
@Data
@AllArgsConstructor

public class Tracker {

    private String internalModeratorId;

    private String externalModeratorId;

    private String trackerId;

}
