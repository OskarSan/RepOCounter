package com.example.repocounter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class ExerciseListAdapter extends RecyclerView.Adapter<ExerciseViewHolder> {
    private Context context;
    private ArrayList<Exercise> exercises = new ArrayList<>();

    public ExerciseListAdapter(ArrayList<Exercise> exercises, Context context) {
        this.exercises = exercises;
        this.context = context;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ExerciseViewHolder(LayoutInflater.from(context).inflate(R.layout.exercise_rv_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        holder.textView.setText(exercises.get(position).getExerciseName());
        holder.imageView.setImageResource(R.drawable.baseline_settings_24);
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }
}
