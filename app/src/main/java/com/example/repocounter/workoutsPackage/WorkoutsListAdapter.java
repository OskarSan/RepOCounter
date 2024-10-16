package com.example.repocounter.workoutsPackage;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repocounter.R;
import com.example.repocounter.Storage;
import com.example.repocounter.activeWorkoutPackage.ActiveWorkoutActivity;

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
            Handler handler = new Handler(Looper.getMainLooper());

            // Create a Runnable for the delayed action (Toast)
            Runnable showToastRunnable = () -> {
                if (tapCount == 1) { // Only show if it's still a single tap
                    Toast.makeText(context, "double-tap to delete", Toast.LENGTH_SHORT).show();
                }
                tapCount = 0; // Reset tap count after showing the message
            };

            if (tapCount == 2) {
                // Cancel the pending toast since the user double-tapped
                handler.removeCallbacks(showToastRunnable);

                // Proceed to remove the workout
                workouts.remove(position);
                notifyItemRemoved(position);
                Storage.getInstance().saveWorkoutsToFile(context);

                tapCount = 0; // Reset tap count
            } else if (tapCount == 1) {
                // Post the runnable with a delay
                handler.postDelayed(showToastRunnable, 200); // 500 milliseconds delay
            }
        });
        holder.workoutStartImageView.setOnClickListener(view -> {
           Intent intent = new Intent(context, ActiveWorkoutActivity.class);
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
