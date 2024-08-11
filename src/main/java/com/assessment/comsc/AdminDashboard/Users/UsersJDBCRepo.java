package com.assessment.comsc.AdminDashboard.Users;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * Repository class for accessing user-related data using JDBC.
 */
@Repository
public class UsersJDBCRepo implements UserRepo {
    
    private JdbcTemplate jdbc;
    private RowMapper<User> userRowMapper;
    private RowMapper<Assessment> assessmentRowMapper;

    /**
     * Constructor to inject JdbcTemplate dependency.
     *
     * @param aJdbc JdbcTemplate instance
     */
    public UsersJDBCRepo(JdbcTemplate aJdbc) {
        this.jdbc = aJdbc;
        setUsersRowMapper();
        setAssessmentsRowMapper();
    }

    /**
     * Method to set up the RowMapper for User objects.
     */
    private void setUsersRowMapper() {
        userRowMapper = (rs, rowNum) -> new User(
            rs.getInt("id"),
            rs.getInt("role"),
            rs.getString("user_name"),
            rs.getString("mail"),
            rs.getString("first_name"),
            rs.getString("last_name"),
            rs.getString("date_of_birth"),
            rs.getString("assigned_roles")
        );
    }

     /**
     * Method to set up the RowMapper for User objects.
     */
    private void setAssessmentsRowMapper() {
        assessmentRowMapper = (rs, rowNum) -> new Assessment(
            rs.getString("assessment_Name"),
            rs.getInt("module_leader_id"),
            rs.getInt("panel_staff_id"),
            rs.getInt("internal_moderator_id"),
            rs.getInt("external_moderator_id"),
            rs.getString("assessment_status"),
            rs.getString("uploaded_assessment_name"),
            rs.getString("module_code"),
            rs.getString("module_name")
        );
    }

    /**
     * Get a list of all users.
     *
     * @return List of User objects representing all users
     */
    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM user_info";
        return jdbc.query(sql, userRowMapper);
    }

    /**
     * Save a new user.
     *
     * @param user User object to be saved
     * @return User object representing the saved user
     */
    @Override
    public User saveUser(User user) {
        String password = "$2a$10$Wm3MaWOpdiGedV26kbsYR.Bm9mto0d5qCTYQQkDfQNCt.IQVM1c1K";

        jdbc.update(
            "INSERT INTO user_info (" +
                "mail, " +
                "password, " +
                "user_name, " +
                "role, " +
                "first_name, " +
                "last_name, " +
                "date_of_birth, " +
                "assigned_roles" +
            ") " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
            user.getEmail(),
            password,
            user.getUserName(),
            user.getRole(),
            user.getFirstName(),
            user.getLastName(),
            user.getDateOfBirth(),
            user.getAssignedRoles()
        );

        return user;
    }

    /**
     * Update an existing user.
     *
     * @param user User object with updated information
     * @return User object representing the updated user
     */
    @Override
    public User updateUser(User user) {
        jdbc.update(
            "UPDATE user_info " +
                "SET " +
                    "mail = ?, " +
                    "user_name = ?, " +
                    "role = ?, " +
                    "first_name = ?, " +
                    "last_name = ?, " +
                    "date_of_birth = ?, " +
                    "assigned_roles = ? " +
                "WHERE " +
                    "id = ?",
            user.getEmail(),
            user.getUserName(),
            user.getRole(),
            user.getFirstName(),
            user.getLastName(),
            user.getDateOfBirth(),
            user.getAssignedRoles(),
            user.getId()
        );

        return user;
    }

    /**
     * Get user details by user ID.
     *
     * @param userId The ID of the user to retrieve
     * @return List of User objects representing user details
     */
    @Override
    public List<User> getUser(int userId) {
        String sql = "SELECT * FROM user_info WHERE id = ?";
        return jdbc.query(sql, userRowMapper, userId);
    }

    /**
     * Get user details by user ID.
     *
     * @param trackerId The ID of the user to retrieve
     * @return List of User objects representing user details
     */
    @Override
    public List<Assessment> getAssessment(int trackerId) {
        String sql = "SELECT " +
        "    * " +
        " FROM tracker t " +
        " INNER JOIN assessments a ON a.id = t.assessment_id " +
        " INNER JOIN modules m ON m.id = a.module_id " +
        " WHERE t.id = ?";
        return jdbc.query(sql, assessmentRowMapper, trackerId);
    }


    /**
     * Update an existing user.
     *
     * @param user User object with updated information
     * @return User object representing the updated user
     */
    @Override
    public Tracker updateTracker(Tracker tracker) {
        jdbc.update(
            "UPDATE tracker " +
            "SET " +
                "internal_moderator_id = ?, " +
                "external_moderator_id = ? " +  // Removed the comma here
            "WHERE id = ?",
            tracker.getInternalModeratorId(),
            tracker.getExternalModeratorId(),
            tracker.getTrackerId()
        );

        return tracker;
    }
}
