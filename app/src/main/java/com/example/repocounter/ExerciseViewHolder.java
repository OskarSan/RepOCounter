package com.example.repocounter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ExerciseViewHolder extends RecyclerView.ViewHolder{
    TextView textView;
    ImageView imageView;


    public ExerciseViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.exerciseNameTextView);
        imageView = itemView.findViewById(R.id.exerciseEditImageView);


    }
}
