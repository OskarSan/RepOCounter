package com.example.repocounter.activeWorkout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repocounter.R;

import java.util.ArrayList;
import java.util.Set;

public class ExerciseInActiveWorkoutLA extends RecyclerView.Adapter<ExerciseInActiveWorkoutVH>{
    private Context context;
    private Integer setAmount;


    public ExerciseInActiveWorkoutLA(Context context, Integer setAmount) {
        this.context = context;
        this.setAmount = setAmount;
    }

    @NonNull
    @Override
    public ExerciseInActiveWorkoutVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ExerciseInActiveWorkoutVH(LayoutInflater.from(context).inflate(R.layout.exercise_in_active_workout_rv_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseInActiveWorkoutVH holder, int position) {
        holder.editExerciseWeightTextNumber.setText("");
        holder.editExerciseRepsTextNumber.setText("");
        holder.editNoteText.setText("");
        holder.checkBox.setChecked(false);
    }

    @Override
    public int getItemCount() {
        return setAmount;
    }
}
