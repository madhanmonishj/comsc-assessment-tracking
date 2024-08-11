package com.assessment.comsc.AvailabilityForm;

import java.util.List;

public interface FormRepository {
    Form save(Form form);

    List<Form> getForms(int assessmentId);
}