package com.example.repocounter.workoutsPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repocounter.R;
import com.example.repocounter.exercisePackage.Exercise;

import java.util.ArrayList;

public class ExerciseInWorkoutListAdapter extends RecyclerView.Adapter<ExerciseInWorkoutViewHolder> {
    private Context context;
    private ArrayList<Exercise> exercises = new ArrayList<>();

    public ExerciseInWorkoutListAdapter(ArrayList<Exercise> exercises, Context context) {
        this.exercises = exercises;
        this.context = context;

    }
    @NonNull
    @Override
    public ExerciseInWorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ExerciseInWorkoutViewHolder(LayoutInflater.from(context).inflate(R.layout.exercise_in_workout_rv_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseInWorkoutViewHolder holder, int position) {
        holder.exerciseNameTextView.setText(exercises.get(position).getExerciseName());
        holder.editTextNumber.setText(exercises.get(position).getSetAmount().toString());
        holder.deleteExerciseInWorkoutImageView.setImageResource(R.drawable.baseline_delete_24);
        holder.deleteExerciseInWorkoutImageView.setOnClickListener(View ->{
            System.out.println(exercises.get(position).getExerciseName());
            exercises.remove(position);
            notifyItemRemoved(position);
        });
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }
}
