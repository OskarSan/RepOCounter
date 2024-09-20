package com.example.repocounter.exercisePackage;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repocounter.R;

public class ExercisesViewHolder extends RecyclerView.ViewHolder{
    TextView typeTextView, nameTextView;
    ImageView exerciseEditImageView, exerciseDeleteImageView;


    public ExercisesViewHolder(@NonNull View itemView) {
        super(itemView);
        typeTextView = itemView.findViewById(R.id.exerciseTypeTextView);
        nameTextView = itemView.findViewById(R.id.exerciseNameTextView);
        exerciseEditImageView = itemView.findViewById(R.id.exerciseEditImageView);
        exerciseDeleteImageView = itemView.findViewById(R.id.exerciseDeleteImageView);

    }


}
