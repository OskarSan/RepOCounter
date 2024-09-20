package com.example.repocounter.workoutsPackage;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repocounter.R;
import com.example.repocounter.Storage;
import com.example.repocounter.activeWorkout.activeWorkoutActivity;

import java.util.ArrayList;

public class WorkoutsListAdapter extends RecyclerView.Adapter<WorkoutsViewHolder> {

    private Context context;
    private ArrayList<Workout> workouts = new ArrayList<>();
    private int tapCount = 0;
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
        holder.workoutDeleteImageView.setImageResource(R.drawable.baseline_delete_24);
        holder.workoutStartImageView.setImageResource(R.drawable.baseline_play_circle_24);
        holder.workoutEditImageView.setOnClickListener(view -> {
            Intent intent = new Intent(context, WorkoutEditActivity.class);
            intent.putExtra("workout", workouts.get(position));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });

        holder.workoutDeleteImageView.setOnClickListener(View->{
            tapCount++;
            if (tapCount == 2) {
                workouts.remove(position);
                notifyItemRemoved(position);
                Storage.getInstance().saveWorkoutsToFile(context);
                tapCount = 0; // Reset tap count

            } else if (tapCount == 1) {

                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    tapCount = 0; // Reset tap count after delay
                    Toast.makeText(context, "double-tap to delete", Toast.LENGTH_SHORT).show();
                }, 200); // 500 milliseconds delay
            }
        });
        holder.workoutStartImageView.setOnClickListener(view -> {
           Intent intent = new Intent(context, activeWorkoutActivity.class);
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
