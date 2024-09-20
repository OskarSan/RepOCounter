package com.example.repocounter.workoutsPackage;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repocounter.R;

public class WorkoutsViewHolder extends RecyclerView.ViewHolder {
    TextView workoutNameTextView;
    ImageView workoutEditImageView, workoutDeleteImageView;

    public WorkoutsViewHolder(@NonNull View itemView) {
        super(itemView);
        workoutNameTextView = itemView.findViewById(R.id.workoutNameTextView);
        workoutEditImageView = itemView.findViewById(R.id.workoutEditImageView);
        workoutDeleteImageView = itemView.findViewById(R.id.workoutDeleteImageVIew);
    }
}
