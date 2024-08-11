package com.assessment.comsc.mapper;

import com.assessment.comsc.entity.ExternalForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JDBCExternalFormRepository implements ExternalFormRepository {
    private JdbcTemplate jdbc;

    @Autowired
    public JDBCExternalFormRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    @Override
    public void insertExternalForm(ExternalForm externalForm) {
        String sql = "INSERT INTO external_form (assessmentId, userId, assessmentStatus, feedback, feedbackDate) " +
                "VALUES (?, ?, ?, ?, ?)";

        jdbc.update(sql,
                externalForm.getAssessmentId(),
                externalForm.getUserId(),
                externalForm.getAssessmentStatus(),
                externalForm.getFeedback(),
                externalForm.getFeedbackDate());

            
        

        jdbc.update(
                "UPDATE tracker SET assessment_status = 'RSP_EXAM_FEED' WHERE id = ?",
                externalForm.getAssessmentId());
    }
}
