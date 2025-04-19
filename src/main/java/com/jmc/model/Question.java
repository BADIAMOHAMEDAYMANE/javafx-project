package com.jmc.model;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private int id;
    private int subjectId;
    private String text;
    private List<Proposition> propositions = new ArrayList<>(); // Initialize empty list

    // Constructors
    public Question(int subjectId, String text) {
        this.subjectId = subjectId;
        this.text = text;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getSubjectId() { return subjectId; }
    public String getText() { return text; }
    public List<Proposition> getPropositions() { return propositions; }
    public void setPropositions(List<Proposition> propositions) {
        this.propositions = propositions != null ? propositions : new ArrayList<>();
    }
}