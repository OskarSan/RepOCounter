package com.example.repocounter.workoutsPackage;

import com.example.repocounter.exercisePackage.Exercise;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class Workout implements Serializable {

    String name, workoutID;
    ArrayList<Exercise> exercises;

    public Workout(String name, ArrayList<Exercise> exercises){
        this.workoutID = UUID.randomUUID().toString();
        this.name = name;
        this.exercises = exercises;
    }


    public String getWorkoutName(){
        return name;
    }

    public ArrayList<Exercise> getExerciseArrayList(){
        return exercises;
    }

    public String getWorkoutID(){ return workoutID; }

    public void setWorkoutName(String name){
        this.name = name;
    }

    public void setExercises(ArrayList<Exercise> exercises){
        this.exercises = exercises;
    }
    public void addExercise(Exercise exercise){
        this.exercises.add(exercise);
    }

    public void removeExercise(Exercise exercise){
        this.exercises.remove(exercise);
    }

    public void setWorkoutID(String workoutID) {
        this.workoutID = workoutID;
    }
}
