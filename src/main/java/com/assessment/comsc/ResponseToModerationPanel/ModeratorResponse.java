package com.assessment.comsc.ResponseToModerationPanel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@AllArgsConstructor
public class ModeratorResponse {
    private String feedback;
    private Boolean editMade;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateCompleted;
    private Boolean assessmentSent;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateSent;
    private Integer assessmentId;

    public ModeratorResponse() {

    }
}