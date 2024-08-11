package com.assessment.comsc.AvailabilityForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/public")
public class FormController {

    private final FormRepository formRepository;

    @Autowired
    private FormController(FormRepository formRepository){
        this.formRepository = formRepository;
    }

    @GetMapping ("/availabilityForm")
    public String view() {
        return "availabilityForm";
    }

    @GetMapping("/getAvailabilityForm")
    @ResponseBody
    public List<Form> getForms(@RequestParam("assessmentId") int assessmentId) {
        return formRepository.getForms(assessmentId);
    }

    @PostMapping("/saveData")
    public String save(@RequestBody Form form){
        formRepository.save(form);
        return "availabilityForm";
    }
}
