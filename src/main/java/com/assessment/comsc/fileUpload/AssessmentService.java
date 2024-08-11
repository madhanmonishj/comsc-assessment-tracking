package com.assessment.comsc.fileUpload;

import com.assessment.comsc.TrackingSheet.Assessment;
import com.assessment.comsc.TrackingSheet.TrackingSheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssessmentService {

    @Autowired
    private TrackingSheetRepository trackingSheetRepository;

    public List<Assessment> getAllAssessments() {
        return trackingSheetRepository.getAllAssessments();
    }
}
