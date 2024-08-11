package com.assessment.comsc.AvailabilityForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JDBCFormRepository implements FormRepository {
    private JdbcTemplate jdbc;

    private RowMapper<Form> formRowMapper;

    @Autowired
    public JDBCFormRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        setFormRowMapper();
    }

    @Override
    public Form save(Form form) {
        // Insert query to insert into database if it was the first time
        if (form.getAvlID() == 0) {
            jdbc.update(
                    "INSERT INTO md_form(if_notified, if_checked, submit_date, times, stu_num, mark_dates, dead_line, summary, assessment_id) "
                            +
                            "SELECT ?, ?, ?, ?, ?, ?, ?, ?, ? " +
                            "FROM dual " +
                            "WHERE NOT EXISTS (SELECT 1 FROM md_form WHERE assessment_id = ?)",
                    form.isIfNotified(),
                    form.isIfChecked(),
                    form.getDate(),
                    form.getTimes(),
                    form.getStuNum(),
                    form.getMarkDates(),
                    form.getDeadline(),
                    form.getSummary(),
                    form.getAssessmentID(),
                    form.getAssessmentID());

            // Update status of the assessment
            jdbc.update(
                    "UPDATE tracker SET assessment_status = 'IN_MOD_C' WHERE id = ?",
                    form.getAssessmentID());                    
        } else {
            // Update query to update the database on occurance of existing records
            jdbc.update(
                    "UPDATE md_form " +
                            "SET if_notified = ?, " +
                            "    if_checked = ?, " +
                            "    submit_date = ?, " +
                            "    times = ?, " +
                            "    stu_num = ?, " +
                            "    mark_dates = ?, " +
                            "    dead_line = ?, " +
                            "    summary = ? " +
                            "WHERE assessment_id = ? " +
                            "AND EXISTS (SELECT 1 FROM md_form WHERE id = ?)",
                    form.isIfNotified(),
                    form.isIfChecked(),
                    form.getDate(),
                    form.getTimes(),
                    form.getStuNum(),
                    form.getMarkDates(),
                    form.getDeadline(),
                    form.getSummary(),
                    form.getAssessmentID(),
                    form.getAvlID());
        }

        return form;
    }

    private void setFormRowMapper(){
        formRowMapper = (rs, rowNum) -> new Form(
                rs.getBoolean("if_notified"),
                rs.getBoolean("if_checked"),
                rs.getString( "submit_date"),
                rs.getString("times"),
                rs.getInt("stu_num"),
                rs.getString("mark_dates"),
                rs.getString("dead_line"),
                rs.getString("summary"),
                rs.getString("assessment_id"),
                rs.getInt("id")
        );
    }
    @Override
    public List<Form> getForms(int assessmentId){
        String sql = "select * "+
                "FROM md_form "+
                "WHERE id = ?";
        return jdbc.query(sql,formRowMapper,assessmentId);
    }
}
