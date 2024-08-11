package com.assessment.comsc.panelStaff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JDBCStaffRepository implements PanelStaffRepository{

    private JdbcTemplate jdbc;
    @Autowired
    public JDBCStaffRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    @Override
    public PanelStaff savefeedback(PanelStaff panelStaff) {
        System.out.println(panelStaff.getConsidered());
        jdbc.update(
                "INSERT INTO panel_feedback(considered,consideration_date,feedback,notified_moderator_setter,notification_date,notified_moduleLeader,moduleLeader_notification_date,assessment_status)"
                        + " values(?,?,?,?,?,?,?,?)",
                panelStaff.getConsidered(),
                panelStaff.getConsiderationDate(),
                panelStaff.getFeedback(),
                panelStaff.getNotifiedModeratorSetter(),
                panelStaff.getNotificationDate(),
                panelStaff.getNotifiedModuleLeader(),
                panelStaff.getModuleLeaderNotificationDate(),
                panelStaff.getAssessmentStatus());

        System.out.println(panelStaff.getAssessmentId());
        jdbc.update(
            "UPDATE tracker SET assessment_status = 'RSP_MOD_P' WHERE id = ?",
            panelStaff.getAssessmentId());

        return panelStaff;
    }
}
