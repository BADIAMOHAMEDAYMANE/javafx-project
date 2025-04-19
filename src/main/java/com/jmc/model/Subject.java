package com.jmc.model;

public class Subject {
    private int id;
    private String name;

    // Constructors, getters, setters
    public Subject(String name) {
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public void setId(int id) { this.id = id; }
}