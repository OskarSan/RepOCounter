package com.example.repocounter.workoutsPackage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repocounter.R;
import com.example.repocounter.exercisePackage.Exercise;

import java.util.ArrayList;

public class ChooseExToWorkoutListAdapter extends RecyclerView.Adapter<ChooseExToWorkoutViewHolder> {

    private Context context;
    private ArrayList<Exercise> exercises = new ArrayList<>();

    public ChooseExToWorkoutListAdapter(ArrayList<Exercise> exercises, Context context) {
        this.exercises = exercises;
        this.context = context;
    }



    @NonNull
    @Override
    public ChooseExToWorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChooseExToWorkoutViewHolder(LayoutInflater.from(context).inflate(R.layout.choose_ex_to_workout_rv_view, parent, false));


    }

    @Override
    public void onBindViewHolder(@NonNull ChooseExToWorkoutViewHolder holder, int position) {
        holder.chooseExerciseToWorkoutTextView.setText(exercises.get(position).getExerciseName());
        holder.chooseExerciseToWorkoutTextView.setOnClickListener(view -> {
            Intent intent = new Intent(context, WorkoutEditActivity.class);
            intent.putExtra("key", exercises.get(position));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }
}
