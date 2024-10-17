package com.example.repocounter.exercisePackage;

public enum ExerciseType {
    BICEPS("Biceps"),
    TRICEPS("Triceps"),
    SHOULDERS("Shoulders"),
    CHEST("Chest"),
    BACK("Back"),
    LEGS("Legs");

    private String displayName;

    ExerciseType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
