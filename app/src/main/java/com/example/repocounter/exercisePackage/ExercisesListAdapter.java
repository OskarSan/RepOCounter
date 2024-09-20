package com.example.repocounter.exercisePackage;

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

import java.util.ArrayList;

public class ExercisesListAdapter extends RecyclerView.Adapter<ExercisesViewHolder> {

    private Context context;
    private ArrayList<Exercise> exercises = new ArrayList<>();
    private int tapCount = 0;

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
        holder.exerciseEditImageView.setImageResource(R.drawable.baseline_settings_24);

        holder.exerciseEditImageView.setOnClickListener(view -> {
            System.out.println(exercises.get(position).getExerciseName());
            Intent intent = new Intent(context, ExerciseEditActivity.class);
            intent.putExtra("exercise", exercises.get(position));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });

        holder.exerciseDeleteImageView.setOnClickListener(view ->{
            tapCount++;
            if (tapCount == 2){
                exercises.remove(position);
                notifyItemRemoved(position);
                Storage.getInstance().saveExercisesToFile(context);
                tapCount = 0;
            } else if (tapCount == 1){
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    tapCount = 0; // Reset tap count after delay
                    Toast.makeText(context, "double-tap to delete", Toast.LENGTH_SHORT).show();
                }, 200); // 500 milliseconds delay
            }


        });

    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }
}
