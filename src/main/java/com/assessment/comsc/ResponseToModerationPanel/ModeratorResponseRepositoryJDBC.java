package com.assessment.comsc.ResponseToModerationPanel;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ModeratorResponseRepositoryJDBC implements ModeratorResponseRepository{

    private JdbcTemplate jdbc;
    private RowMapper<ModeratorResponse> moderatorResponseRowMapper;

    public ModeratorResponseRepositoryJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
        this.setModeratorResponseRowMapper();

    }

    private void setModeratorResponseRowMapper() {
        this.moderatorResponseRowMapper = ((rs, rowNum) -> {
            return new ModeratorResponse(rs.getString("feedback"),
                    rs.getBoolean("editMade"),
                    rs.getDate("dateCompleted"),
                    rs.getBoolean("assessmentSent"),
                    rs.getDate("dateSent"),
                    rs.getInt("assessment_id")
                    );
        });
    }
    public ModeratorResponse save(ModeratorResponse moderatorResponse) {
        jdbc.update("INSERT INTO response_to_moderation_panel(feedback, editMade, dateCompleted, assessmentSent, dateSent) VALUES(?, ?, ?, ?, ?)",
                moderatorResponse.getFeedback(),
                moderatorResponse.getEditMade(),
                moderatorResponse.getDateCompleted() != null ? new java.sql.Date(moderatorResponse.getDateCompleted().getTime()) : null,
                moderatorResponse.getAssessmentSent(),
                moderatorResponse.getDateSent() != null ? new java.sql.Date(moderatorResponse.getDateSent().getTime()) : null);

        jdbc.update(
                "UPDATE tracker SET assessment_status = 'EXT_EXAM_FEED' WHERE id = ?",
                moderatorResponse.getAssessmentId());
        
        return moderatorResponse;
    }

}



