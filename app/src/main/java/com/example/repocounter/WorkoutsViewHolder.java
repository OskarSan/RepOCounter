package com.example.repocounter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WorkoutsViewHolder extends RecyclerView.ViewHolder {
    TextView workoutNameTextView;
    ImageView workoutEditImageView;

    public WorkoutsViewHolder(@NonNull View itemView) {
        super(itemView);
        workoutNameTextView = itemView.findViewById(R.id.workoutNameTextView);
        workoutEditImageView = itemView.findViewById(R.id.workoutEditImageView);
    }
}
