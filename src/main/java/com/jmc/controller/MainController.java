package com.jmc.controller;

import com.jmc.dao.PropositionDao;
import com.jmc.dao.QuestionDao;
import com.jmc.dao.SubjectDao;
import com.jmc.dao.impl.PropositionDaoImpl;
import com.jmc.dao.impl.QuestionDaoImpl;
import com.jmc.dao.impl.SubjectDaoImpl;
import com.jmc.model.Subject;
import com.jmc.service.QuestionService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MainController {
    private QuestionService questionService;

    @FXML
    private VBox subjectsContainer;

    public void initialize() {
        try {
            // Initialize services
            questionService = initializeServices();

            // Load subjects from database
            List<Subject> subjects = questionService.getAllSubjects();

            // Create buttons for each subject
            createSubjectButtons(subjects);

        } catch (SQLException e) {
            e.printStackTrace();
            // Show error to user
        }
    }

    private QuestionService initializeServices() throws SQLException {
        // Initialize your DAOs here
        SubjectDao subjectDao = new SubjectDaoImpl();
        QuestionDao questionDao = new QuestionDaoImpl();
        PropositionDao propositionDao = new PropositionDaoImpl();

        return new QuestionService(subjectDao, questionDao, propositionDao);
    }

    private void createSubjectButtons(List<Subject> subjects) {
        subjectsContainer.getChildren().clear();

        for (Subject subject : subjects) {
            Button subjectButton = new Button(subject.getName());
            subjectButton.setPrefWidth(200);
            subjectButton.setPrefHeight(50);

            // Set action to load quiz for this subject
            subjectButton.setOnAction(event -> loadQuiz(subject));

            subjectsContainer.getChildren().add(subjectButton);
        }
    }

    private void loadQuiz(Subject subject) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/quiz-view.fxml"));
            Parent root = loader.load();

            // Get controller and pass the subject
            QuizController quizController = loader.getController();
            quizController.initializeQuiz(subject, questionService);

            // Switch scene
            Stage stage = (Stage) subjectsContainer.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(subject.getName() + " Quiz");

        } catch (IOException e) {
            e.printStackTrace();
            // Show error to user
        }
    }
}