package com.assessment.comsc.controller;

import com.assessment.comsc.dto.DataResult;
import com.assessment.comsc.entity.ExternalForm;
import com.assessment.comsc.mapper.ExternalFormMapper;
import com.assessment.comsc.mapper.JDBCExternalFormRepository;
import com.assessment.comsc.service.UserInfoService;
import com.assessment.comsc.tool.AuthorityTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/external")
public class ExternalFormController {


    @Autowired
    private JDBCExternalFormRepository jdbcExternalFormRepository;

    @Autowired
    private UserInfoService userInfoService;


    @PostMapping("/saveForm")
    @ResponseBody
    public DataResult save(@RequestBody ExternalForm externalForm) {
        DataResult result = new DataResult();

        String userName;
        try {
            userName = AuthorityTool.getUserName();
        } catch (Exception e) {
            result.failed(null, e.getMessage());
            return result;
        }
        externalForm.setUserId(userInfoService.getUserInfoByMail(userName).getUserId());

        //TODO: next week link the form information to the related Assessment.
        jdbcExternalFormRepository.insertExternalForm(externalForm);
        result.succeed("success");
        return result;
    }


//    @PostMapping("/queryForm")
//    public DataResult query(@RequestBody ExternalForm externalForm) {
//
//        ExternalForm data = externalFormMapper.selectExternalFormById(externalForm.getAssessmentId());
//        DataResult result = new DataResult();
//        result.succeed(data);
//        return result;
//    }
}
