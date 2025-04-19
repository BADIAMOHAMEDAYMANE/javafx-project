package com.jmc.controller;

import com.jmc.model.Proposition;
import com.jmc.model.Question;
import com.jmc.model.Subject;
import com.jmc.service.QuestionService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuizController {
    @FXML private Label subjectLabel;
    @FXML private Label questionLabel;
    @FXML private VBox propositionsContainer;
    @FXML private Button submitButton;
    @FXML private Button nextButton;
    @FXML private Button finishButton;
    @FXML private Label feedbackLabel;

    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private QuestionService questionService;
    private ToggleGroup toggleGroup;
    private List<Integer> selectedIndices = new ArrayList<>();

    public void initializeQuiz(Subject subject, QuestionService questionService) {
        this.questionService = questionService;
        this.toggleGroup = new ToggleGroup();

        try {
            questions = questionService.getQuestionsForSubject(subject.getName());

            if (questions.isEmpty()) {
                questionLabel.setText("No questions available for " + subject.getName());
                return;
            }

            subjectLabel.setText(subject.getName() + " Quiz");
            displayCurrentQuestion();

        } catch (SQLException e) {
            e.printStackTrace();
            questionLabel.setText("Error loading questions");
        }
    }

    private void displayCurrentQuestion() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        questionLabel.setText(currentQuestion.getText());
        propositionsContainer.getChildren().clear();
        selectedIndices.clear();
        submitButton.setDisable(true);
        feedbackLabel.setText("");

        // For multiple choice questions
        for (int i = 0; i < currentQuestion.getPropositions().size(); i++) {
            Proposition proposition = currentQuestion.getPropositions().get(i);
            RadioButton radioButton = new RadioButton(proposition.getText());
            radioButton.setToggleGroup(toggleGroup);
            radioButton.setUserData(i);

            // For multiple answers (if needed)
            CheckBox checkBox = new CheckBox(proposition.getText());
            checkBox.setUserData(i);
            checkBox.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
                if (isSelected) {
                    selectedIndices.add((Integer) checkBox.getUserData());
                } else {
                    selectedIndices.remove((Integer) checkBox.getUserData());
                }
                submitButton.setDisable(selectedIndices.isEmpty());
            });

            propositionsContainer.getChildren().add(checkBox);
        }
    }

    @FXML
    private void handleSubmit() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        boolean isCorrect = true;

        // Check all selected answers
        for (int i = 0; i < currentQuestion.getPropositions().size(); i++) {
            Proposition p = currentQuestion.getPropositions().get(i);
            boolean shouldBeSelected = p.isCorrect();
            boolean isSelected = selectedIndices.contains(i);

            if (shouldBeSelected != isSelected) {
                isCorrect = false;
                break;
            }
        }

        if (isCorrect) {
            score++;
            feedbackLabel.setText("Correct!");
            feedbackLabel.setStyle("-fx-text-fill: green;");
        } else {
            feedbackLabel.setText("Incorrect!");
            feedbackLabel.setStyle("-fx-text-fill: red;");
        }

        submitButton.setDisable(true);
        nextButton.setVisible(true);

        if (currentQuestionIndex == questions.size() - 1) {
            finishButton.setVisible(true);
            nextButton.setVisible(false);
        }
    }

    @FXML
    private void handleNextQuestion() {
        currentQuestionIndex++;
        displayCurrentQuestion();
        nextButton.setVisible(false);
    }

    @FXML
    private void handleFinishQuiz() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/result-view.fxml"));
            Parent root = loader.load();

            ResultController controller = loader.getController();
            controller.setResults(score, questions.size());

            Stage stage = (Stage) finishButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Quiz Results");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}