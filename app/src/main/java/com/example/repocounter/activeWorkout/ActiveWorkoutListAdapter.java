package com.example.repocounter.activeWorkout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repocounter.R;
import com.example.repocounter.exercisePackage.Exercise;
import com.example.repocounter.workoutsPackage.Workout;

import java.util.ArrayList;

public class ActiveWorkoutListAdapter extends RecyclerView.Adapter<ActiveWorkoutViewHolder> {
    private Context context;
    private ArrayList<Exercise> workoutExercises = new ArrayList<>();



    public ActiveWorkoutListAdapter(Context context, ArrayList<Exercise> exercises) {
        this.context = context;
        this.workoutExercises = exercises;
    }

    @NonNull
    @Override
    public ActiveWorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ActiveWorkoutViewHolder(LayoutInflater.from(context).inflate(R.layout.active_workout_rv_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ActiveWorkoutViewHolder holder, int position) {
        holder.activeWorkoutExerciseNameTextView.setText(workoutExercises.get(position).getExerciseName());
        holder.exerciseInActiveWorkoutRV.setLayoutManager(new LinearLayoutManager(context));
        holder.exerciseInActiveWorkoutRV.setAdapter(new ExerciseInActiveWorkoutLA(context, workoutExercises.get(position)));
        holder.addSetButton.setOnClickListener(view -> {
            workoutExercises.get(position).setExerciseSets(workoutExercises.get(position).getSets() + 1);
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return workoutExercises.size();
    }

    public ArrayList<Exercise> getWorkoutExercises(){
        return workoutExercises;
    }

}
