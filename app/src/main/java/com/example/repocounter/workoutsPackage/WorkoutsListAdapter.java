package com.example.repocounter.workoutsPackage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repocounter.R;

import java.util.ArrayList;

public class WorkoutsListAdapter extends RecyclerView.Adapter<WorkoutsViewHolder> {

    private Context context;
    private ArrayList<Workout> workouts = new ArrayList<>();

    public WorkoutsListAdapter(ArrayList<Workout> workouts, Context context){
        this.workouts = workouts;
        this.context = context;
    }

    @NonNull
    @Override
    public WorkoutsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WorkoutsViewHolder(LayoutInflater.from(context).inflate(R.layout.workouts_rv_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutsViewHolder holder, int position) {
        holder.workoutNameTextView.setText(workouts.get(position).getWorkoutName());
        holder.workoutEditImageView.setImageResource(R.drawable.baseline_settings_24);
        holder.workoutEditImageView.setOnClickListener(view -> {
            Intent intent = new Intent(context, WorkoutEditActivity.class);
            intent.putExtra("workout", workouts.get(position));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }
}
