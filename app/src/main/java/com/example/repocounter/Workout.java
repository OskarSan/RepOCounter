package com.example.repocounter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Workout implements Serializable {

    String name;
    ArrayList<Exercise> exercises;

    public Workout(String name, ArrayList<Exercise> exercises){
        this.name = name;
        this.exercises = exercises;
    }


    public String getWorkoutName(){
        return name;
    }

    public ArrayList<Exercise> getExercises(){
        return exercises;
    }


}
