package com.assessment.comsc.feedbackform;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@Component
public class ScoreDataRetriever {

    public List<Double> retrieveScoresFromDatabase() {
        List<Double> scoresList = new ArrayList<>();

        // Database connection information
        String jdbcUrl = "jdbc:mariadb://localhost:3306/assessmenttracking";
        String username = "root";
        String password = "comsc";

        try {

            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            String query = "SELECT score FROM assessmentfeedback";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Iterate through the result set and add scores to scoresList.
            while (resultSet.next()) {
                double score = resultSet.getDouble("score");
                scoresList.add(score);
            }


            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return scoresList;
    }
}
