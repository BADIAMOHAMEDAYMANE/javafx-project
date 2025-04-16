package com.jmc.model;


import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private final String subject;
    private final List<Question> questions;

    public Quiz(String subject) {
        this.subject = subject;
        this.questions = new ArrayList<>();
        initializeQuestions();
    }

    private void initializeQuestions() {
        if (subject.equals("Sports")) {
            questions.add(new Question(
                    "Which sports use a ball?",
                    List.of("Basketball", "Swimming", "Tennis", "Cycling"),
                    List.of(0, 2) // First and third options are correct
            ));
            questions.add(new Question(
                    "Which of these are team sports?",
                    List.of("Golf", "Soccer", "Basketball", "Tennis"),
                    List.of(1, 2) // Soccer and Basketball
            ));
        } else if (subject.equals("Politics")) {
            questions.add(new Question(
                    "Which of these are forms of government?",
                    List.of("Democracy", "Monarchy", "Oligarchy", "Anarchy"),
                    List.of(0, 1, 2, 3) // All are correct
            ));
        } else if (subject.equals("History")) {
            questions.add(new Question(
                    "Which events happened in the 20th century?",
                    List.of("World War I", "French Revolution", "Moon Landing", "American Civil War"),
                    List.of(0, 2) // WWI and Moon Landing
            ));
        }
    }

    public String getSubject() {
        return subject;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}