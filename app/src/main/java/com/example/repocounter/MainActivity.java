package com.example.repocounter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button routinesActivityButton = (Button) findViewById(R.id.routines_button);
        Button exercisesActivityButton = (Button) findViewById(R.id.exercises_button);
        Button statistcsActivityButton = (Button) findViewById(R.id.statistics_button);

        Storage.getInstance().saveExercisesToFile(getApplicationContext());
        Storage.getInstance().loadExercisesFromFile(getApplicationContext());

        routinesActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), WorkoutsActivity.class);
                startActivity(startIntent);

            }
        });

        exercisesActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), ExercisesActivity.class);
                startActivity(startIntent);
            }
        });

        statistcsActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), StatisticsActivity.class);
                startActivity(startIntent);
            }
        });


    }
}