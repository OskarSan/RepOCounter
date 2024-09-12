package com.example.repocounter;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class Storage {

    private static Storage storage = null;
    public static Storage getInstance(){
        if(storage == null){
            storage = new Storage();
            storage.addExercise(new Exercise(ExerciseType.PUSH, "pena", "pena", 10, 10 ));
            storage.addExercise(new Exercise(ExerciseType.PULL, "vetoo", "vetoilu", 10, 10 ));
            storage.addExercise(new Exercise(ExerciseType.LEGS, "kykky", "kykky", 10, 10 ));
            storage.addExercise(new Exercise(ExerciseType.PUSH, "vinopena", "vinoilupena", 10, 10 ));

            storage.addWorkout(new Workout("test", storage.getExerciseArrayList()));
            storage.addWorkout(new Workout("test2", storage.getExerciseArrayList()));

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

    public void saveExercisesToFile(Context context){
        try {
            ObjectOutputStream exerciseWriter = new ObjectOutputStream(context.openFileOutput("exercises.ser", Context.MODE_PRIVATE));
            exerciseWriter.writeObject(exerciseArrayList);
            exerciseWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadExercisesFromFile(Context context){
        try {
            ObjectInputStream exerciseReader = new ObjectInputStream(context.openFileInput("exercises.ser"));
            exerciseArrayList = (ArrayList<Exercise>) exerciseReader.readObject();
            exerciseReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
