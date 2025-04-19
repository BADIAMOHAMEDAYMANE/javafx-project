package com.jmc.model;

import java.util.List;

public class Quiz {
    private final String subject;
    private final List<Question> questions;
    private int currentQuestionIndex;
    private int score;

    public Quiz(String subject, List<Question> questions) {
        this.subject = subject;
        this.questions = questions;
        this.currentQuestionIndex = 0;
        this.score = 0;
    }

    // Getters only - immutable after creation
    public String getSubject() { return subject; }
    public List<Question> getQuestions() { return questions; }
    public int getCurrentQuestionIndex() { return currentQuestionIndex; }
    public int getScore() { return score; }
    public int getTotalQuestions() { return questions.size(); }

    // Quiz progression methods
    public Question getCurrentQuestion() {
        return questions.get(currentQuestionIndex);
    }

    public boolean hasNextQuestion() {
        return currentQuestionIndex < questions.size() - 1;
    }

    public void moveToNextQuestion() {
        if (hasNextQuestion()) {
            currentQuestionIndex++;
        }
    }

    public void incrementScore() {
        score++;
    }
}