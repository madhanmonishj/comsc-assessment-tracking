package com.assessment.comsc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.assessment.comsc.AvailabilityForm.Form;
import com.assessment.comsc.AvailabilityForm.FormController;
import com.assessment.comsc.AvailabilityForm.FormRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class FormControllerTest {

    @Mock
    private FormRepository formRepository;

    @InjectMocks
    private FormController formController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(formController).build();
    }

    @Test
    void testViewPage() throws Exception {
        mockMvc.perform(get("/public/availabilityForm"))
                .andExpect(status().isOk())  // Ensure HTTP 200 OK response
                .andExpect(view().name("availabilityForm"));
    }


    @Test
    void testGetForms() throws Exception {
        // Mocking the behavior of formRepository.getForms
        Form testData = new Form(
                true, false, "2023-11-11", "tomorrow", 321, "2023-12-8", "123456", "no", "1", 0
        );
        when(formRepository.getForms(anyInt())).thenReturn(Collections.singletonList(testData));

        // Performing the GET request to the endpoint
        mockMvc.perform(get("/public/getAvailabilityForm?assessmentId=1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].ifNotified").value(testData.isIfNotified()))
                .andExpect(jsonPath("$[0].ifChecked").value(testData.isIfChecked()))
                .andExpect(jsonPath("$[0].date").value(testData.getDate()))
                .andExpect(jsonPath("$[0].times").value(testData.getTimes()))
                .andExpect(jsonPath("$[0].stuNum").value(testData.getStuNum()))
                .andExpect(jsonPath("$[0].markDates").value(testData.getMarkDates()))
                .andExpect(jsonPath("$[0].deadline").value(testData.getDeadline()))
                .andExpect(jsonPath("$[0].summary").value(testData.getSummary()));
    }

    @Test
    void testSave() throws Exception {
        Form form = new Form(true,false,"2023-11-12","tomorrow",123,"2023-12-23","2024-1-2","no","1",0);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonForm = objectMapper.writeValueAsString(form);
        ResultActions resultActions = mockMvc.perform(post("/public/saveData")
                .content(jsonForm)
                .contentType("application/json"));
        resultActions.andExpect(status().isOk());
    }
}


