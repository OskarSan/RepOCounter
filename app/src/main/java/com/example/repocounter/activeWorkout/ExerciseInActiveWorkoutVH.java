package com.example.repocounter.activeWorkout;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repocounter.R;

public class ExerciseInActiveWorkoutVH extends RecyclerView.ViewHolder {
    EditText editExerciseRepsTextNumber, editExerciseWeightTextNumber, editNoteText ;
    CheckBox checkBox;

    public ExerciseInActiveWorkoutVH(@NonNull View itemView) {
        super(itemView);

        editExerciseWeightTextNumber = itemView.findViewById(R.id.editExerciseWeightTextNumber);
        editExerciseRepsTextNumber = itemView.findViewById(R.id.editExerciseRepsTextNumber);
        editNoteText = itemView.findViewById(R.id.editNoteText);
        checkBox = itemView.findViewById(R.id.checkBox);
    }
}
