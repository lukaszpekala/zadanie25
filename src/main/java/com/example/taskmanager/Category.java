package com.example.taskmanager;

public enum Category {
    HOME("dom"),
    WORK("praca");

    private final String description;

    Category(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}