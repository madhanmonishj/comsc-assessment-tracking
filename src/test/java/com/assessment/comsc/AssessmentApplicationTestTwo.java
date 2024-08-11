package com.assessment.comsc;
import com.assessment.comsc.entity.ModuleLeaderForm;
import com.assessment.comsc.mapper.ModuleLeaderMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Date;
@SpringBootTest
class AssessmentApplicationTestTwo {
    @Autowired
    private ModuleLeaderMapper mapper;

    @Test
    public void test() {
        ModuleLeaderForm moduleLeaderForm = new ModuleLeaderForm();
        moduleLeaderForm.setUserId(2);
        moduleLeaderForm.setFeedback("test feedback");
        moduleLeaderForm.setEditsMade(false);
        moduleLeaderForm.setCompletedDate(new Date());
        moduleLeaderForm.setPanelNotify(false);
        moduleLeaderForm.setNotifyDate(new Date());
        moduleLeaderForm.setAssessmentId(0);

        mapper.insert(moduleLeaderForm);
        System.out.println(moduleLeaderForm.toString());
        moduleLeaderForm.setCompletedDate(new Date());
        moduleLeaderForm.setNotifyDate(new Date());
        mapper.update(moduleLeaderForm);
        System.out.println(moduleLeaderForm.toString());
        System.out.println("insert or update success");
    }

}