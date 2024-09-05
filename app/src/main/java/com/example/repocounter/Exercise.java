package com.example.repocounter;

import java.io.Serializable;

public class Exercise implements Serializable {
    protected String exerciseName, exerciseDescription;
    protected Integer reps, weight;

    //kuvasetti
    protected int image = R.drawable.ic_launcher_foreground;


    public Exercise(String name, String description, Integer reps, Integer weight){
        this.exerciseName = name;
        this.exerciseDescription = description;
        this.reps = reps;
        this.weight = weight;
    }

    public String getExerciseName(){return exerciseName;}
    public String getExerciseDescription(){return exerciseDescription;}
    public Integer getReps(){return reps;}

}
