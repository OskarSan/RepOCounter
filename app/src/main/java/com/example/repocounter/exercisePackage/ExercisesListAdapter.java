package com.example.repocounter.exercisePackage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repocounter.R;

import java.util.ArrayList;

public class ExercisesListAdapter extends RecyclerView.Adapter<ExercisesViewHolder> {

    private Context context;
    private ArrayList<Exercise> exercises = new ArrayList<>();

    public ExercisesListAdapter(ArrayList<Exercise> exercises, Context context) {
        this.exercises = exercises;
        this.context = context;
    }

    @NonNull
    @Override
    public ExercisesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ExercisesViewHolder(LayoutInflater.from(context).inflate(R.layout.exercises_rv_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ExercisesViewHolder holder, int position) {
        holder.typeTextView.setText(exercises.get(position).getExerciseType().getDisplayName() + ": ");
        holder.nameTextView.setText(exercises.get(position).getExerciseName());
        holder.imageView.setImageResource(R.drawable.baseline_settings_24);

        holder.imageView.setOnClickListener(view -> {
            System.out.println(exercises.get(position).getExerciseName());
            Intent intent = new Intent(context, ExerciseEditActivity.class);
            intent.putExtra("exercise", exercises.get(position));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }
}
