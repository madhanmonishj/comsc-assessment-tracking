package com.assessment.comsc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is a text controller
 */
@RestController
@RequestMapping("/test")
public class HelloWorldController {

    @GetMapping("/hello")
    public String hello() {
        return "hello world  +  success";
    }
}