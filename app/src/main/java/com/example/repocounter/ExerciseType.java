package com.example.repocounter;

public enum ExerciseType {
    PUSH("Push"),
    PULL("Pull"),
    LEGS("Legs");

    private String displayName;

    ExerciseType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
