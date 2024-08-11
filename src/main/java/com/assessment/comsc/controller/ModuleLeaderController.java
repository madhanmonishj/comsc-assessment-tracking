package com.assessment.comsc.controller;

import com.assessment.comsc.entity.ModuleLeaderForm;
import com.assessment.comsc.mapper.ModuleLeaderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Action;

@RestController
public class ModuleLeaderController {

    @Autowired
    private ModuleLeaderMapper mapper;

    @PostMapping("/RSTInterModule")
    public ResponseEntity<String> handleModuleSetterRequest(@RequestBody ModuleLeaderForm form) {
        mapper.insert(form);

        mapper.update(form);
        // Return response
        return ResponseEntity.ok("Data received successfully");
    }

}
