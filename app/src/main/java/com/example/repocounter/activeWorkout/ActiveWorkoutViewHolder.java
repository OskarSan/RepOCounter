package com.example.repocounter.activeWorkout;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repocounter.R;

public class ActiveWorkoutViewHolder extends RecyclerView.ViewHolder {
    TextView activeWorkoutExerciseNameTextView;
    RecyclerView exerciseInActiveWorkoutRV;
    Button addSetButton;
    public ActiveWorkoutViewHolder(@NonNull View itemView) {
        super(itemView);
        activeWorkoutExerciseNameTextView = itemView.findViewById(R.id.activeWorkoutExerciseNameTextView);
        exerciseInActiveWorkoutRV = itemView.findViewById(R.id.exerciseInActiveWorkoutRV);
        addSetButton = itemView.findViewById(R.id.addSetButton);
    }


}
