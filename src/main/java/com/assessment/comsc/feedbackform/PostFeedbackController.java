package com.assessment.comsc.feedbackform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostFeedbackController {
    @Autowired
    private  PostFeedbackRepository postFeedbackRepository;
    
    @PostMapping("/feedback")
    public ResponseEntity<String> savePostFeedback(@RequestBody PostFeedback postfeedback) {
        postFeedbackRepository.savePostFeedback(postfeedback);
        return ResponseEntity.ok("Data received successfully");
    }

}
