package com.assessment.comsc.feedbackform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class scoreController {

    private final ScoreDataRetriever scoreDataRetriever;

    @Autowired
    public scoreController(ScoreDataRetriever scoreDataRetriever) {
        this.scoreDataRetriever = scoreDataRetriever;
    }

    @GetMapping("/calculateStats")
    public ResponseEntity<Map<String, Double>> calculateStats() {
        List<Double> scoresList = scoreDataRetriever.retrieveScoresFromDatabase();

        // Calculation of average scores
        Double averageScore = scoresList.isEmpty() ? 0.0 :
                scoresList.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);

        // Calculate the minimum value
        Double minScore = scoresList.isEmpty() ? 0.0 : Collections.min(scoresList);

        // Calculate the maximum value
        Double maxScore = scoresList.isEmpty() ? 0.0 : Collections.max(scoresList);

        // Store the results in a Map
        Map<String, Double> stats = Map.of(
                "averageScore", averageScore,
                "minScore", minScore,
                "maxScore", maxScore
        );

        // Return results
        return ResponseEntity.ok(stats);
    }
}
