package com.assessment.comsc.TrackingSheet;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class TrackingRepoJDBC implements TrackingSheetRepository{
    private JdbcTemplate jdbc;
    private RowMapper<Module> modulesRowMapper;
    private RowMapper<Assessment> assessmentRowMapper;

    // Constructor to inject JdbcTemplate dependency
    public TrackingRepoJDBC(JdbcTemplate aJdbc){    
        this.jdbc = aJdbc;
        setmodulesRowMapper();
        setAssessmentRowMapper();
    }

    // Method to set up the RowMapper for Module objects
    private void  setmodulesRowMapper() {
        modulesRowMapper = (rs, rowNum) -> new Module(
            rs.getInt("module_id"),
            rs.getInt("assessment_id"),
            rs.getInt("tracker_id"),
            rs.getString("module_code"),
            rs.getString("module_name"),
            rs.getString("assessment_name"),
            rs.getString("assessment_status"),
            rs.getInt("avail_id"),
            rs.getInt("mod_panel_com_id"),
            rs.getString("uploaded_assessment_name"),
            rs.getInt("panel_staff_id"),
            rs.getInt("module_leader_id"),
            rs.getInt("internal_moderator_id"),
            rs.getInt("external_moderator_id"));
    }

    private void setAssessmentRowMapper() {
        assessmentRowMapper = (rs, rowNum) -> new Assessment(
            rs.getInt("module_id"),
            rs.getInt("assessment_id"),
            rs.getString("module_code"),
            rs.getString("module_name"),
            rs.getString("assessment_name"));
    }

    @Override
    public List<Module> getAllModules() {
        // SQL query to fetch modules and related assessments data
        String sql = "SELECT " +
                        "m.id AS module_id, " +
                        "a.id AS assessment_id, " +
                        "t.id AS tracker_id, " +
                        "a.*, " +
                        "m.*, " +
                        "t.*, " +
                        "mf.id AS avail_id, " +
                        "mc.id AS mod_panel_com_id " +
                    "FROM " +
                    "    modules m " +
                    "    INNER JOIN assessments a ON a.module_id = m.id " +
                    "    INNER JOIN tracker t ON t.assessment_id = a.id " +
                    "    LEFT JOIN md_form mf ON mf.assessment_id = t.id" +
                    "    LEFT JOIN moderation_comments mc ON t.id = mc.assessment_id";

        return jdbc.query(sql, modulesRowMapper);
    }

    @Override
    public List<Assessment> getAllAssessments() {

        String sql = "SELECT " +
                        "a.id AS assessment_id, " +
                        "a.*, " +
                        "m.* " +
                    "FROM " +
                    "    modules m " +
                    "    INNER JOIN assessments a ON a.module_id = m.id ";

        return jdbc.query(sql, assessmentRowMapper);
    }

}
