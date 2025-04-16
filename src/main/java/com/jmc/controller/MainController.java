package com.jmc.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private Button sportsButton;

    @FXML
    private Button politicsButton;

    @FXML
    private Button historyButton;

    @FXML
    private void handleSportsButton(ActionEvent event) {
        startQuiz("Sports");
    }

    @FXML
    private void handlePoliticsButton(ActionEvent event) {
        startQuiz("Politics");
    }

    @FXML
    private void handleHistoryButton(ActionEvent event) {
        startQuiz("History");
    }

    private void startQuiz(String subject) {
        try {
            // Load the quiz view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/quiz-view.fxml"));
            Parent root = loader.load();

            // Get the controller and pass the subject
            QuizController controller = loader.getController();
            controller.setQuizSubject(subject);

            // Show the quiz scene
            Stage stage = (Stage) sportsButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(subject + " Quiz");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}