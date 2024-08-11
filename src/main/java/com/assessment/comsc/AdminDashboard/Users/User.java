package com.assessment.comsc.AdminDashboard.Users;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents a user in the system.
 */
@Data
@AllArgsConstructor

public class User {

    private Integer id;

    private Integer role;

    private String userName;

    private String email;

    private String firstName;

    private String lastName;

    private String dateOfBirth;

    // Roles assigned to the user as a comma-separated string
    private String assignedRoles;

}