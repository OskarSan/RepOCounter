package com.example.repocounter;

import java.util.ArrayList;
import java.util.Iterator;

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
    public ArrayList<Workout> getWorkoutArrayList(){
        return workoutArrayList;
    }

    public Exercise findExerciseById(String id){
        for(Exercise exercise : exerciseArrayList){
            if(exercise.exerciseID.equals(id)){
                return exercise;
            }
        }
        return null;
    }

    public void editExercise(Exercise newExercise, String oldID) {
        Iterator<Exercise> iterator = exerciseArrayList.iterator();
        while (iterator.hasNext()) {
            Exercise ex = iterator.next();
            if (ex.exerciseID.equals(oldID)) {
                iterator.remove(); // Use iterator.remove() to safely remove the element
                newExercise.setExerciseID(oldID);
                exerciseArrayList.add(newExercise);
                break; // Exit the loop after replacing the exercise
            }
        }
        //sortExercisesByType();

    }

    public void sortExercisesByType(){
        exerciseArrayList.sort((o1, o2) -> o1.getExerciseType().compareTo(o2.getExerciseType()));
    }


}
