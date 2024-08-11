package com.assessment.comsc;
import com.assessment.comsc.feedbackform.PostFeedback;
import com.assessment.comsc.feedbackform.PostFeedbackRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

@SpringBootTest
@AutoConfigureMockMvc
public class PostFeedbackControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostFeedbackRepository postFeedbackRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSavePostFeedback() throws Exception {
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
        String postFeedbackJson = objectMapper.writeValueAsString(postFeedback);

        // Perform a POST request to the "/feedback" endpoint
        mockMvc.perform(MockMvcRequestBuilders.post("/feedback")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postFeedbackJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Data received successfully"));
    }
}