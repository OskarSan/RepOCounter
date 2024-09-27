package com.example.repocounter.exercisePackage;

import com.example.repocounter.R;

import java.io.Serializable;
import java.util.UUID;

public class Exercise implements Serializable {
    protected ExerciseType exerciseType;
    protected String exerciseName, exerciseDescription, exerciseID, notes;
    protected Integer reps, weight, sets;

    //kuvasetti
    protected int image = R.drawable.ic_launcher_foreground;

    public Exercise ( ExerciseType type, String name, String description, Integer reps, Integer weight){
        this.exerciseID = UUID.randomUUID().toString();
        this.exerciseType = type;
        this.exerciseName = name;
        this.exerciseDescription = description;
        this.reps = reps;
        this.weight = weight;
        this.sets = 0;
        this.notes = "";
    }


    public String getExerciseName(){return exerciseName;}
    public ExerciseType getExerciseType(){return exerciseType;}
    public String getExerciseID(){return exerciseID;}
    public Integer getWeight(){return weight;}
    public String getExerciseDescription(){return exerciseDescription;}
    public Integer getReps(){return reps;}
    public Integer getSets(){return sets;}

    public void setWeight(int weight){
      this.weight = weight;
    };

    public void setReps(int reps){
        this.reps = reps;
    };

    public void setnotes(String notes){
        this.notes = notes;
    }

    public void setExerciseID(String exerciseID) {
        this.exerciseID = exerciseID;
    }
    public void setExerciseSets(Integer sets){
        this.sets = sets;
    }
}
