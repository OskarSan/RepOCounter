package com.example.repocounter.activeWorkoutPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repocounter.R;
import com.example.repocounter.exercisePackage.Exercise;

public class ExerciseInActiveWorkoutLA extends RecyclerView.Adapter<ExerciseInActiveWorkoutVH>{
    private Context context;
    private Exercise exercise;
    private Integer setAmount;


    public ExerciseInActiveWorkoutLA(Context context, Exercise exercise) {
        this.context = context;
        this.exercise = exercise;

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

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
           if (isChecked){
               exercise.setWeight(Integer.parseInt(holder.editExerciseWeightTextNumber.getText().toString().trim()));
               exercise.setReps(Integer.parseInt(holder.editExerciseRepsTextNumber.getText().toString().trim()));
               exercise.setnotes(holder.editNoteText.getText().toString().trim());

               System.out.println("checked");

               holder.editExerciseWeightTextNumber.setEnabled(false);
               holder.editExerciseRepsTextNumber.setEnabled(false);
               holder.editNoteText.setEnabled(false);
           } else{
               System.out.println("unchecked");

               holder.editExerciseWeightTextNumber.setEnabled(true);
               holder.editExerciseRepsTextNumber.setEnabled(true);
               holder.editNoteText.setEnabled(true);
           }
        });


    }

    @Override
    public int getItemCount() {
        return exercise.getSets();
    }
}
