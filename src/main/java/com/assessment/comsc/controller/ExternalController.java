package com.assessment.comsc.controller;

import com.assessment.comsc.mapper.ExternalFormMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This is an external page controller
 */
@Controller
@CrossOrigin
@RequestMapping("/externalpage")
public class ExternalController {

    @GetMapping("/form")
    public String hello() {
        return "externalForm";
    }
}