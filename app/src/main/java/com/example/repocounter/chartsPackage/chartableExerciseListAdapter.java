package com.example.repocounter.chartsPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repocounter.R;
import com.example.repocounter.exercisePackage.Exercise;
import com.example.repocounter.statisticsPackage.WorkoutLogEntry;
import com.example.repocounter.workoutsPackage.Workout;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class chartableExerciseListAdapter extends RecyclerView.Adapter<ChartableExerciseViewHolder>{
    private Context context;
    private ArrayList<Exercise> exercises;
    private HashMap<LocalDate, WorkoutLogEntry> workoutLogs;
    public chartableExerciseListAdapter(Context context, ArrayList<Exercise> exercises, HashMap<LocalDate, WorkoutLogEntry> workoutLogs){
        this.context = context;
        this.exercises = exercises;
        this.workoutLogs = workoutLogs;
    }

    @NonNull
    @Override
    public ChartableExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChartableExerciseViewHolder(LayoutInflater.from(context).inflate(R.layout.charts_rv_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChartableExerciseViewHolder holder, int position) {
        holder.chartableExerciseNameTextView.setText(exercises.get(position).getExerciseName());
        holder.chartExerciseImageView.setImageResource(R.drawable.baseline_insert_chart_outlined_24);
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }
}
