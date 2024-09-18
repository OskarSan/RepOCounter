package com.example.repocounter.workoutsPackage;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repocounter.R;

public class ChooseExToWorkoutViewHolder extends RecyclerView.ViewHolder {
    TextView chooseExerciseToWorkoutTextView;

    public ChooseExToWorkoutViewHolder(@NonNull View itemView) {
        super(itemView);
        chooseExerciseToWorkoutTextView = itemView.findViewById(R.id.chooseExerciseToWorkoutTextView);

    }
}
