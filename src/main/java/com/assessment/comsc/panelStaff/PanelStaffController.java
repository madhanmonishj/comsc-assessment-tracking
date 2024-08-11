package com.assessment.comsc.panelStaff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping("/public")
public class PanelStaffController {

    private final PanelStaffRepository panelStaffRepository;
    @Autowired
    private PanelStaffController(PanelStaffRepository panelStaffRepository){this.panelStaffRepository = panelStaffRepository;}
    
    @GetMapping("/panelStaff")
    public String view() {
        return "panelStaff";
    }

    @PostMapping("/saveFeedback")
    public String save(@RequestBody PanelStaff panelStaff){
        System.out.println(panelStaff);
        panelStaffRepository.savefeedback(panelStaff);
        return "panelStaff";
    }
}
