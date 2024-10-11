package com.example.repocounter.activeWorkoutPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repocounter.R;
import com.example.repocounter.Storage;
import com.example.repocounter.exercisePackage.Exercise;
import com.example.repocounter.exercisePackage.Set;
import com.example.repocounter.statisticsPackage.WorkoutLogEntry;
import com.example.repocounter.workoutsPackage.Workout;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;


public class ExerciseInActiveWorkoutLA extends RecyclerView.Adapter<ExerciseInActiveWorkoutVH>{
    private Context context;
    private Exercise exercise;
    private Integer setAmount;
    private Workout workout;


    public ExerciseInActiveWorkoutLA(Context context, Exercise exercise, Workout workout) {
        this.context = context;
        this.exercise = exercise;
        this.workout = workout;
    }

    @NonNull
    @Override
    public ExerciseInActiveWorkoutVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ExerciseInActiveWorkoutVH(LayoutInflater.from(context).inflate(R.layout.exercise_in_active_workout_rv_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseInActiveWorkoutVH holder, int position) {


        Set set = exercise.getSetList().get(position);
        HashMap<LocalDate, WorkoutLogEntry>  workoutLog = Storage.getInstance().getWorkoutLog();
        ArrayList<Exercise> prevExercises = null;

        ArrayList<WorkoutLogEntry> entries = new ArrayList<>(workoutLog.values()); // Get values as ArrayList
        ListIterator<WorkoutLogEntry> iterator = entries.listIterator(entries.size()); // Start at the end

        //finding the previous workout, doesnt work
        while (iterator.hasPrevious()) {
            WorkoutLogEntry entry = iterator.previous();
            if (entry.getWorkout().getWorkoutID() == workout.getWorkoutID()){
                    prevExercises = entry.getWorkout().getExerciseArrayList();
                    System.out.println("got it");
                }
            }


        if(prevExercises != null){
            holder.editExerciseWeightTextNumber.setText(String.valueOf(prevExercises.get(position).getSetList().get(position).getWeight()));
            holder.editExerciseRepsTextNumber.setText(String.valueOf(prevExercises.get(position).getSetList().get(position).getReps()));
            holder.editNoteText.setText(prevExercises.get(position).getSetList().get(position).getNotes());
        }else{
            holder.editExerciseWeightTextNumber.setText(String.valueOf(set.getWeight()));
            holder.editExerciseRepsTextNumber.setText(String.valueOf(set.getReps()));
            holder.editNoteText.setText(set.getNotes());
        }
        holder.checkBox.setChecked(false);

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
           if (isChecked){
               //exercise.getSetList(position).adsdasdasd
               set.setWeight(Integer.parseInt(holder.editExerciseWeightTextNumber.getText().toString().trim()));
               set.setReps(Integer.parseInt(holder.editExerciseRepsTextNumber.getText().toString().trim()));
               set.setnotes(holder.editNoteText.getText().toString().trim());

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
        return exercise.getSetAmount();
    }
}
