package com.example.repocounter.workoutsPackage;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repocounter.R;

public class ExerciseInWorkoutViewHolder extends RecyclerView.ViewHolder {

    TextView exerciseNameTextView;
    TextView setAmountTextView;
    EditText editTextNumber;
    ImageView deleteExerciseInWorkoutImageView;

    public ExerciseInWorkoutViewHolder(@NonNull View itemView) {
        super(itemView);
        exerciseNameTextView = itemView.findViewById(R.id.exerciseInWorkoutNameTextView);
        setAmountTextView = itemView.findViewById(R.id.setAmountTextView);
        editTextNumber = itemView.findViewById(R.id.editTextNumber);
        deleteExerciseInWorkoutImageView = itemView.findViewById(R.id.deleteExerciseInWorkoutImageView);

    }

}
