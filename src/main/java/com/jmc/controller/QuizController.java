package com.jmc.controller;

import com.jmc.model.Question;
import com.jmc.model.Quiz;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuizController {
    private Quiz quiz;
    private int currentQuestionIndex = 0;
    private List<Integer> selectedOptions = new ArrayList<>();

    @FXML
    private Label subjectLabel;
    @FXML
    private Label questionLabel;
    @FXML
    private VBox optionsContainer;
    @FXML
    private Button submitButton;
    @FXML
    private Label feedbackLabel;
    @FXML
    private Button nextButton;
    @FXML
    private Button finishButton;

    public void setQuizSubject(String subject) {
        this.quiz = new Quiz(subject);
        subjectLabel.setText(subject + " Quiz");
        displayQuestion();
    }

    private void displayQuestion() {
        // Clear previous state
        optionsContainer.getChildren().clear();
        selectedOptions.clear();
        submitButton.setDisable(true);
        feedbackLabel.setText("");
        nextButton.setVisible(false);
        finishButton.setVisible(false);

        if (currentQuestionIndex < quiz.getQuestions().size()) {
            Question currentQuestion = quiz.getQuestions().get(currentQuestionIndex);
            questionLabel.setText(currentQuestion.getText());

            // Create checkboxes for each option
            for (int i = 0; i < currentQuestion.getOptions().size(); i++) {
                CheckBox checkBox = new CheckBox(currentQuestion.getOptions().get(i));
                final int optionIndex = i;
                checkBox.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
                    if (isSelected) {
                        selectedOptions.add(optionIndex);
                    } else {
                        selectedOptions.remove(Integer.valueOf(optionIndex));
                    }
                    submitButton.setDisable(selectedOptions.isEmpty());
                });
                optionsContainer.getChildren().add(checkBox);
            }
        } else {
            // Quiz completed
            questionLabel.setText("Quiz completed!");
            optionsContainer.getChildren().clear();
            finishButton.setVisible(true);
        }
    }

    @FXML
    private void handleSubmit() {
        Question currentQuestion = quiz.getQuestions().get(currentQuestionIndex);
        boolean isCorrect = selectedOptions.equals(currentQuestion.getCorrectAnswers());

        if (isCorrect) {
            feedbackLabel.setText("Correct!");
            feedbackLabel.setStyle("-fx-text-fill: green;");
        } else {
            feedbackLabel.setText("Incorrect! The correct answers were: " +
                    currentQuestion.getCorrectAnswers().toString());
            feedbackLabel.setStyle("-fx-text-fill: red;");
        }

        submitButton.setDisable(true);
        nextButton.setVisible(true);
    }

    @FXML
    private void handleNextQuestion() {
        currentQuestionIndex++;
        displayQuestion();
    }

    @FXML
    private void handleFinishQuiz() {
        // Return to main menu
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/main-view.fxml"));
            Stage stage = (Stage) finishButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("QCM Quiz");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}