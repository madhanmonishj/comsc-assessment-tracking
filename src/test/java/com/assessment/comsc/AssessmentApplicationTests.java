package com.assessment.comsc;

import com.assessment.comsc.TrackingSheet.Assessment;
import com.assessment.comsc.entity.ExternalForm;
import com.assessment.comsc.fileUpload.AssessmentService;
import com.assessment.comsc.fileUpload.FileInfo;
import com.assessment.comsc.fileUpload.FileInfoRepository;
import com.assessment.comsc.mapper.JDBCExternalFormRepository;
import com.assessment.comsc.service.UserInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AssessmentApplicationTests {

    @Test
    void contextLoads() {
    }


    @Autowired
    private AssessmentService assessmentService;

    /**
     * try to get assessment list
     */
    @Test
    public void fetchAssessments() {
        System.out.println("check all assessment");

        //try to get all information of assessment and print it in the console
        List<Assessment> allAssessments = assessmentService.getAllAssessments();
        assertNotNull(allAssessments, "assessment should not be null");

        for (Assessment allAssessment : allAssessments) {
            System.out.println(allAssessment.toString());
        }

        System.out.println("print all assessments success");
    }


    @Autowired
    private FileInfoRepository fileInfoRepository;

    @Autowired
    private UserInfoService userInfoService;

    /**
     * insert the file detail to the table file_info
     */
    @Test
    public void insertFileWithName() {
        System.out.println("start storage file");

        // Get the file name
        String fileName = "testFileName.pdf";

        // Build the file path
        String filePath = "test/file/path";

        //Create virtual data and insert the database
        FileInfo fileInfo = new FileInfo();
        fileInfo.setAssessmentId(1);

        //user account for testing
        String userName = "google@gmail.com";

        fileInfo.setUserId(userInfoService.getUserInfoByMail(userName).getUserId());
        fileInfo.setUrl(filePath);
        fileInfo.setFileName(fileName);
        fileInfo.setUploadTime(new Date());

        //insert the file
        fileInfoRepository.insertFileInfo(fileInfo);
        System.out.println("insert file success");
    }

    @Autowired
    private JDBCExternalFormRepository jdbcExternalFormRepository;

    @Test
    public void saveExternalFormData() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //Simulate login matching password
        //username
        String userName = "google@gmail.com";
        //Encrypted password
        String Password = "$2a$10$Wm3MaWOpdiGedV26kbsYR.Bm9mto0d5qCTYQQkDfQNCt.IQVM1c1K";
        //real password
        String userInputPassword = "123";

        // Use the matches method to verify whether the password matches.
        if (passwordEncoder.matches(userInputPassword, userInputPassword)) {
            System.out.println("Password Matched!");
        } else {
            System.out.println("Password Not Matched!");
        }
        //insert the data
        Long userId = userInfoService.getUserInfoByMail(userName).getUserId();
        ExternalForm textFormData = new ExternalForm();
        textFormData.setAssessmentId(1);
        textFormData.setUserId(userId);
        textFormData.setAssessmentStatus(1);
        textFormData.setFeedback("test feedback content");
        textFormData.setFeedbackDate(new Date());

        jdbcExternalFormRepository.insertExternalForm(textFormData);
    }
}
