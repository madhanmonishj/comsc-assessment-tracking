package com.assessment.comsc.fileUpload;

import com.assessment.comsc.TrackingSheet.Assessment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fetchAssessments")
public class AssessmentController {

    @Autowired
    private AssessmentService assessmentService;


    /**
     * for page to show the list of assessment UPLOADING PAGE!
     * @return
     */
    @GetMapping
    public List<Assessment> fetchAssessments() {
        return assessmentService.getAllAssessments();
    }
}
