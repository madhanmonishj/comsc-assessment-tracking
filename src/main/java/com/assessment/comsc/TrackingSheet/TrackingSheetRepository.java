package com.assessment.comsc.TrackingSheet;

import java.util.List;

// Interface contract for modules
public interface TrackingSheetRepository {
    List <Module> getAllModules();

    List <Assessment> getAllAssessments();
}
