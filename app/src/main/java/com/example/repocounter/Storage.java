package com.example.repocounter;

import java.util.ArrayList;
import java.util.HashMap;

public class Storage {

    private static Storage storage = null;
    public static Storage getInstance(){
        if(storage == null){
            storage = new Storage();
        }
        return storage;
    }

    ArrayList<Exercise> exerciseArrayList = new ArrayList<>();
    ArrayList<Workout> workoutArrayList = new ArrayList<>();

    private Integer exerciseID = 0, workoutID = 0;

    public void addExercise(Exercise exercise){
        exerciseArrayList.add(exerciseID, exercise);
        exerciseID +=1;
    }

    public void addWorkout(Workout workout){
        workoutArrayList.add(workoutID, workout);
        workoutID +=1;
    }

    public ArrayList<Exercise> getExerciseArrayList(){
        return exerciseArrayList;
    }
    public ArrayList<Workout> getwWorkoutHashMap(){
        return workoutArrayList;
    }


}
