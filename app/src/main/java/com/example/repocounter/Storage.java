package com.example.repocounter;

import java.util.ArrayList;

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
        System.out.println(exerciseArrayList.size());
        return exerciseArrayList;
    }
    public ArrayList<Workout> getwWorkoutHashMap(){
        return workoutArrayList;
    }

    public void editExercise(Exercise exercise) {
        for (int i = 0; i < exerciseArrayList.size(); i++) {
            if (exerciseArrayList.get(i).exerciseName.equals(exercise.exerciseName)) {
                exerciseArrayList.set(i, exercise);
                break;
            }
        }
    }


}
