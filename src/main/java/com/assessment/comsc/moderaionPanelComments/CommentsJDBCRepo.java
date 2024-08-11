package com.assessment.comsc.moderaionPanelComments;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentsJDBCRepo implements CommentRepo {
    private JdbcTemplate jdbc;
    private RowMapper<Comment> commentsRowMapper;

    public CommentsJDBCRepo(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        setCommentsRowMapper();
    }

    @Override
    public Comment save(Comment comment) {
        // Insert query and update status for new records
        if (comment.getModPanId() == 0) {
            jdbc.update(
                "INSERT INTO moderation_comments (" +
                    "isLinkClear, " +
                    "isActivityClear, " +
                    "isCriteriaClear, " +
                    "isClassClear, " +
                    "isWorkDone, " +
                    "isMistakeFree, " +
                    "isRequirementsClear, " +
                    "isSubArrange, " +
                    "isPenaltySub, " +
                    "isFeedReturn, " +
                    "isMarkingPlan, " +
                    "assessment_id," +
                    "comments, " +
                    "isScrutiny, " +
                    "isModerated, " +
                    "dateCompleted " +
                " ) " +
                "SELECT ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? " +
                "FROM dual " +
                "WHERE NOT EXISTS ( " +
                    "SELECT 1 FROM moderation_comments WHERE id = ?" +
                ")",

                comment.getIsLinkClear(),
                comment.getIsActivityClear(),
                comment.getIsCriteriaClear(),
                comment.getIsClassClear(),
                comment.getIsWorkDone(),
                comment.getIsMistakeFree(),
                comment.getIsRequirementsClear(),
                comment.getIsSubArrange(),
                comment.getIsPenaltySub(),
                comment.getIsFeedReturn(),
                comment.getIsMarkingPlan(),
                comment.getAssessmentId(),
                comment.getComments(),
                comment.getIsScrutiny(),
                comment.getIsModerated(),
                comment.getDateCompleted(),
                comment.getAssessmentId());

                jdbc.update(
                    "UPDATE tracker SET assessment_status = 'RSP_IN_MOD' WHERE id = ?",
                    comment.getAssessmentId());
        } else {
            //update query to update existing records
            jdbc.update("UPDATE moderation_comments " +
                "SET " +
                "  isLinkClear = ?, " +
                "  isActivityClear = ?, " +
                "  isCriteriaClear = ?, " +
                "  isClassClear = ?, " +
                "  isWorkDone = ?, " +
                "  isMistakeFree = ?, " +
                "  isRequirementsClear = ?, " +
                "  isSubArrange = ?, " +
                "  isPenaltySub = ?, " +
                "  isFeedReturn = ?, " +
                "  isMarkingPlan = ?, " +
                "  comments = ?, " +
                "  isScrutiny = ?, " +
                "  isModerated = ?, " +
                "  dateCompleted = ? " +
                "WHERE id = ?",

                comment.getIsLinkClear(),
                comment.getIsActivityClear(),
                comment.getIsCriteriaClear(),
                comment.getIsClassClear(),
                comment.getIsWorkDone(),
                comment.getIsMistakeFree(),
                comment.getIsRequirementsClear(),
                comment.getIsSubArrange(),
                comment.getIsPenaltySub(),
                comment.getIsFeedReturn(),
                comment.getIsMarkingPlan(),
                comment.getComments(),
                comment.getIsScrutiny(),
                comment.getIsModerated(),
                comment.getDateCompleted(),
                comment.getModPanId()
            );


        }

        return comment;
    }

    // Method to set up the RowMapper for Module objects
    private void setCommentsRowMapper() {
        commentsRowMapper = (rs, rowNum) -> new Comment(
                rs.getBoolean("isLinkClear"),
                rs.getBoolean("isActivityClear"),
                rs.getBoolean("isCriteriaClear"),
                rs.getBoolean("isClassClear"),
                rs.getBoolean("isWorkDone"),
                rs.getBoolean("isMistakeFree"),
                rs.getBoolean("isRequirementsClear"),
                rs.getBoolean("isSubArrange"),
                rs.getBoolean("isPenaltySub"),
                rs.getBoolean("isFeedReturn"),
                rs.getBoolean("isMarkingPlan"),
                rs.getInt("assessment_id"),
                rs.getString("comments"),
                rs.getBoolean("isScrutiny"),
                rs.getBoolean("isModerated"),
                rs.getString("dateCompleted"),
                rs.getInt("id"));
    }

    // select query to return record with the assessment id
    @Override
    public List<Comment> getAllComments(int assessmentId) {
        String sql = "SELECT * " +
                "FROM " +
                "moderation_comments mc " +
                "WHERE assessment_id = ?";

        return jdbc.query(sql, commentsRowMapper, assessmentId);
    }
}