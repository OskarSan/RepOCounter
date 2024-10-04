package com.example.repocounter.workoutsPackage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repocounter.MainActivity;
import com.example.repocounter.R;
import com.example.repocounter.Storage;
import com.example.repocounter.exercisePackage.ExercisesActivity;

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

        storage.loadWorkoutsFromFile(getApplicationContext());

        workoutsRecyclerView = findViewById(R.id.workoutsRecyclerView);
        workoutsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        workoutsRecyclerView.setAdapter(new WorkoutsListAdapter(storage.getWorkoutArrayList(), getApplicationContext()));

        Button addWorkoutButton = findViewById(R.id.addWorkoutButton);
        addWorkoutButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), WorkoutEditActivity.class);
            intent.putExtra("key", "new");
            startActivity(intent);
        });
        Button workoutBackButton = findViewById(R.id.workoutBackButton);
        workoutBackButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });




    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Clear the activity stack
        startActivity(intent);
        finish(); // Finish the current activity
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Storage.getInstance().saveWorkoutsToFile(getApplicationContext());
    }

}