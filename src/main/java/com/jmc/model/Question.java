package com.jmc.model;

import java.util.List;

public class Question {
    private String text;
    private List<String> options;
    private List<Integer> correctAnswers;

    public Question(String text, List<String> options, List<Integer> correctAnswers) {
        this.text = text;
        this.options = options;
        this.correctAnswers = correctAnswers;
    }

    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }

    public List<Integer> getCorrectAnswers() {
        return correctAnswers;
    }
}