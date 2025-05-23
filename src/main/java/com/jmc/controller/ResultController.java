package com.jmc.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ResultController {
    @FXML private Label scoreLabel;
    @FXML private Label percentageLabel;

    public void setResults(int score, int totalQuestions) {
        double percentage = (double) score / totalQuestions * 100;
        scoreLabel.setText(String.format("You scored %d out of %d", score, totalQuestions));
        percentageLabel.setText(String.format("Percentage: %.1f%%", percentage));
    }

    @FXML
    private void handleReturn() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/main-view.fxml"));
            Stage stage = (Stage) scoreLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Quiz Selection");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}