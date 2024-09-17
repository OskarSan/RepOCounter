package com.example.repocounter.exercisePackage;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repocounter.R;

public class ExercisesViewHolder extends RecyclerView.ViewHolder{
    TextView typeTextView, nameTextView;
    ImageView imageView;


    public ExercisesViewHolder(@NonNull View itemView) {
        super(itemView);
        typeTextView = itemView.findViewById(R.id.exerciseTypeTextView);
        nameTextView = itemView.findViewById(R.id.exerciseNameTextView);
        imageView = itemView.findViewById(R.id.exerciseEditImageView);


    }


}
