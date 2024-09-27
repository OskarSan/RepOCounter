package com.example.repocounter;

import android.os.Build;

import com.example.repocounter.workoutsPackage.Workout;

import java.time.LocalDate;
import java.util.UUID;

public class WorkoutLogEntry {

    LocalDate date;
    Workout workout;
    String workoutLogId;

    public WorkoutLogEntry(Workout workout){
        this.workout = workout;
        this.workoutLogId = UUID.randomUUID().toString();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.date = LocalDate.now();
        }else{
            this.date = null;
        }

    }
    public LocalDate getDate(){
        return date;
    }
    public Workout getWorkout(){
        return workout;
    }
    public String getWorkoutLogId() {
        return workoutLogId;
    }


}
