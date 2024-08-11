package com.assessment.comsc.TrackingSheet;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data

// constructor class for Assessments
public class Assessment {
    private Integer module_id;

    private Integer assessment_id;

    private String assessment_name;

    private String module_code;

    private String module_name;

}
