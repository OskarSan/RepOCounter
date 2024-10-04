package com.example.repocounter.chartsPackage;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repocounter.R;

public class ChartableExerciseViewHolder extends RecyclerView.ViewHolder {

    TextView chartableExerciseNameTextView;
    ImageView chartExerciseImageView;
    public ChartableExerciseViewHolder(@NonNull View itemView) {
        super(itemView);
        chartableExerciseNameTextView = itemView.findViewById(R.id.exerciseNameInChartListTV);
        chartExerciseImageView = itemView.findViewById(R.id.imageView);

    }
}
