package com.assessment.comsc.feedbackform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JDBCPostFeedback implements PostFeedbackRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JDBCPostFeedback(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public PostFeedback savePostFeedback(PostFeedback postFeedback) {

        jdbcTemplate.update(
                "INSERT INTO assessmentfeedback (score_date, feedback_date, score, feedback, " +
                        "assessment_marks_released, date_assessment_marks_released, assessment_feedback_released, " +
                        "date_assessment_feedback_released, assessment_marks_entered, date_assessment_marks_entered, " +
                        "assessment_marks_transferred, date_assessment_marks_transferred, assessment_feedback_entered, " +
                        "date_assessment_feedback_entered, cohort_feedback_returned, date_cohort_feedback_returned) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                postFeedback.getScoreDate(),
                postFeedback.getFeedbackDate(),
                postFeedback.getScore(),
                postFeedback.getFeedback(),
                postFeedback.getAssessmentMarksReleased(),
                postFeedback.getDateAssessmentMarksReleased(),
                postFeedback.getAssessmentFeedbackReleased(),
                postFeedback.getDateAssessmentFeedbackReleased(),
                postFeedback.getAssessmentMarksEntered(),
                postFeedback.getDateAssessmentMarksEntered(),
                postFeedback.getAssessmentMarksTransferred(),
                postFeedback.getDateAssessmentMarksTransferred(),
                postFeedback.getAssessmentFeedbackEntered(),
                postFeedback.getDateAssessmentFeedbackEntered(),
                postFeedback.getCohortFeedbackReturned(),
                postFeedback.getDateCohortFeedbackReturned()
        );

        return postFeedback;
    }
    @Override
    public Double calculateAverageScore() {
        String sql = "SELECT AVG(score) FROM assessmentfeedback";
        return jdbcTemplate.queryForObject(sql, Double.class);
    }
}
