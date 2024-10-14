package com.example.repocounter.statisticsPackage;

import android.os.Build;

import com.example.repocounter.workoutsPackage.Workout;

import java.time.LocalDateTime;
import java.util.UUID;

public class WorkoutLogEntry  implements java.io.Serializable {

    LocalDateTime date;
    Workout workout;
    String workoutLogId;

    public WorkoutLogEntry(Workout workout){
        this.workout = workout;
        this.workoutLogId = UUID.randomUUID().toString();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.date = LocalDateTime.now();
            System.out.println("date = "+date);
        }else{
            this.date = null;
        }

    }
    public LocalDateTime getDate(){
        return date;
    }
    public Workout getWorkout(){
        return workout;
    }
    public String getWorkoutLogId() {
        return workoutLogId;
    }


}
