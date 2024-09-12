package com.example.repocounter;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class WorkoutsActivity extends AppCompatActivity {
    private Storage storage;
    private RecyclerView workoutsRecyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_workouts);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        storage = storage.getInstance();
        /*
        storage.loadExercisesFromFile(getApplicationContext());
        storage.sortExercisesByType();
        */
        workoutsRecyclerView = findViewById(R.id.workoutsRecyclerView);
        workoutsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        workoutsRecyclerView.setAdapter(new WorkoutsListAdapter(storage.getWorkoutArrayList(), getApplicationContext()));

        Button addWorkoutButton = findViewById(R.id.addWorkoutButton);
        addWorkoutButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), WorkoutEditActivity.class);
            intent.putExtra("key", "new");
            startActivity(intent);
        });

    }
}