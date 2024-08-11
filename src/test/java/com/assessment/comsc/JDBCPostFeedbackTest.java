package com.assessment.comsc;

import com.assessment.comsc.feedbackform.JDBCPostFeedback;
import com.assessment.comsc.feedbackform.PostFeedback;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Date;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class JDBCPostFeedbackTest {

    @Autowired
    private JDBCPostFeedback postFeedbackRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testSavePostFeedback() {
        // Create a sample PostFeedback object for testing
        PostFeedback postFeedback = new PostFeedback();

        postFeedback.setAssessmentId(123);
        postFeedback.setUserId(456L);
        postFeedback.setScoreDate(new Date());
        postFeedback.setFeedbackDate(new Date());
        postFeedback.setScore(9.5);
        postFeedback.setFeedback("good");
        postFeedback.setAssessmentMarksReleased(true);
        postFeedback.setDateAssessmentMarksReleased(new Date());
        postFeedback.setAssessmentFeedbackReleased(true);
        postFeedback.setDateAssessmentFeedbackReleased(new Date());
        postFeedback.setAssessmentMarksEntered(true);
        postFeedback.setDateAssessmentMarksEntered(new Date());
        postFeedback.setAssessmentMarksTransferred(true);
        postFeedback.setDateAssessmentMarksTransferred(new Date());
        postFeedback.setAssessmentFeedbackEntered(true);
        postFeedback.setDateAssessmentFeedbackEntered(new Date());
        postFeedback.setCohortFeedbackReturned(true);
        postFeedback.setDateCohortFeedbackReturned(new Date());

        // Call the savePostFeedback method
        PostFeedback savedFeedback = postFeedbackRepository.savePostFeedback(postFeedback);

        // Verify that the feedback was saved successfully
        assertNotNull(savedFeedback);
    }

    @Test
    public void testCalculateAverageScore() {
        // Insert sample data into the database for testing
        jdbcTemplate.update("INSERT INTO assessmentfeedback (score) VALUES (10)");
        jdbcTemplate.update("INSERT INTO assessmentfeedback (score) VALUES (20)");

        // Call the calculateAverageScore method
        Double averageScore = postFeedbackRepository.calculateAverageScore();

        // Verify that the average score is calculated correctly
        assertEquals(15.0, Objects.requireNonNull(averageScore, "Average score should not be null"));
    }
}
