package com.assessment.comsc.ResponseToModerationPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@CrossOrigin
@RequestMapping("/public")
public class ModeratorResponseController {

    @Autowired
    private ModeratorResponseRepository moderatorResponseRepository;

    @Autowired
    public ModeratorResponseController(ModeratorResponseRepository moderatorResponseRepository) {
        this.moderatorResponseRepository = moderatorResponseRepository;
    }

    @GetMapping("/respMod")
    public ModelAndView showModerationResponseForm() {
        ModelAndView modelAndView = new ModelAndView("moderatorResponse");
        modelAndView.addObject("moderatorResponse", new ModeratorResponse());
        return modelAndView;
    }

    @PostMapping("/savingModerationForm")
    public String submitForm(@RequestBody ModeratorResponse moderatorResponse) {
        moderatorResponseRepository.save(moderatorResponse);
        return "moderatorResponse";
    }
}


