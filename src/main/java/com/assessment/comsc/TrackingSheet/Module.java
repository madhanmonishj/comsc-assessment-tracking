package com.assessment.comsc.TrackingSheet;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

// constructor class for Module
public class Module {
    private int module_id;

    private int assessment_id;

    private int tracker_id;

    private String module_code;

    private String module_name;

    private String assessment_name;

    private String assessment_status;

    private int avl_id;

    private int mod_panel_com_id;

    private String uploaded_assessment_name;

    private Integer panel_staff_id;

    private Integer module_leader_id;

    private Integer internal_moderator_id;

    private Integer external_moderator_id;
}
