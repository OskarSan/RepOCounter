package com.example.repocounter;

import java.io.Serializable;
import java.util.UUID;

public class Exercise implements Serializable {
    protected ExerciseType exerciseType;
    protected String exerciseName, exerciseDescription, exerciseID;
    protected Integer reps, weight;

    //kuvasetti
    protected int image = R.drawable.ic_launcher_foreground;

    public Exercise ( ExerciseType type, String name, String description, Integer reps, Integer weight){
        this.exerciseID = UUID.randomUUID().toString();
        this.exerciseType = type;
        this.exerciseName = name;
        this.exerciseDescription = description;
        this.reps = reps;
        this.weight = weight;

    }


    public String getExerciseName(){return exerciseName;}
    public ExerciseType getExerciseType(){return exerciseType;}
    public String getExerciseID(){return exerciseID;}
    public Integer getWeight(){return weight;}
    public String getExerciseDescription(){return exerciseDescription;}
    public Integer getReps(){return reps;}

    public void setExerciseID(String exerciseID) {
        this.exerciseID = exerciseID;
    }
}
