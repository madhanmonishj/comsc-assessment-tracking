package com.assessment.comsc.TrackingSheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TrackingController {
    @Autowired
    private TrackingSheetRepository trackingSheetRepository;

    // GET requests to "/tracking" and render template
    @GetMapping("/tracking")

    public ModelAndView moduleList(){
        ModelAndView nav = new ModelAndView("trackingSheet");
        List<Module> Modules = trackingSheetRepository.getAllModules();
        nav.addObject("modules", Modules);

        return  nav;
    }

    // GET requests to "/tracking/lists" to fetch data from database
    @GetMapping("tracking/lists")
    @ResponseBody
    public List<Module> getModuleData() {
        return trackingSheetRepository.getAllModules();
    }

}
