package com.example.repocounter;

import android.content.Context;

import com.example.repocounter.exercisePackage.Exercise;
import com.example.repocounter.exercisePackage.ExerciseType;
import com.example.repocounter.workoutsPackage.Workout;

import java.io.ObjectInputStream;
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
            if(exercise.getExerciseID().equals(id)){
                return exercise;
            }
        }
        return null;
    }

    public void editExercise(Exercise newExercise, String oldID) {
        Iterator<Exercise> iterator = exerciseArrayList.iterator();
        while (iterator.hasNext()) {
            Exercise ex = iterator.next();
            if (ex.getExerciseID().equals(oldID)) {
                iterator.remove(); // Use iterator.remove() to safely remove the element
                newExercise.setExerciseID(oldID);
                exerciseArrayList.add(newExercise);
                break; // Exit the loop after replacing the exercise
            }
        }


    }

    public void editWorkout(Workout newWorkout, String oldID){
        Iterator<Workout> iterator = workoutArrayList.iterator();
        while (iterator.hasNext()) {
            Workout workout = iterator.next();
            if (workout.getWorkoutID().equals(oldID)){
                iterator.remove();
                newWorkout.setWorkoutID(oldID);
                workoutArrayList.add(newWorkout);
                break;
            }
        }
    }



    public void sortExercisesByType(){
        exerciseArrayList.sort((o1, o2) -> o1.getExerciseType().compareTo(o2.getExerciseType()));
    }
    public void sortWorkoutsByName(){
        workoutArrayList.sort((o1, o2) -> o1.getWorkoutName().compareTo(o2.getWorkoutName()));
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

    public void saveWorkoutsToFile(Context context){
        try {
            ObjectOutputStream workoutWriter = new ObjectOutputStream(context.openFileOutput("workouts.ser", Context.MODE_PRIVATE));
            workoutWriter.writeObject(workoutArrayList);
            workoutWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void loadWorkoutsFromFile(Context context){
        try {
            ObjectInputStream workoutReader = new ObjectInputStream(context.openFileInput("workouts.ser"));
            workoutArrayList = (ArrayList<Workout>) workoutReader.readObject();
            workoutReader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
