package com.assessment.comsc.controller;
import com.assessment.comsc.feedbackform.PostFeedback;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/loginPage")
    public String tologin() {

        return "login";
    }
    @GetMapping("/rsInterModel")
    public String RS_inter_moder(Model model) {

        return "rsInterModer";
    }
    @GetMapping("/GetFeedback")
    public String postFeed(PostFeedback postFeedback) {
        return "postFeedback";
    }


}
