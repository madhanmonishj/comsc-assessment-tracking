package com.assessment.comsc.fileUpload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FileInfoJdbcRepository implements FileInfoRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FileInfoJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertFileInfo(FileInfo fileInfo) {
        String sql = "INSERT INTO file_info (assessment_id, user_id, url, file_name, upload_time) " +
                     "VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(
                sql,
                fileInfo.getAssessmentId(),
                fileInfo.getUserId(),
                fileInfo.getUrl(),
                fileInfo.getFileName(),
                fileInfo.getUploadTime()
        );
        // Updated internal and external moderator id in tracker table
        jdbcTemplate.update(
                "INSERT INTO tracker (" +
                    "assessment_id, " +
                    "user_id, " +
                    "assessment_status, " +
                    "uploaded_assessment_name" +
                " ) " +
                "SELECT ?, ?, ?, ? " +
                "FROM dual " ,
                fileInfo.getAssessmentId(),
                fileInfo.getUserId(),
                "AS_AVL",
                fileInfo.getFileName()
        );
    }
}
