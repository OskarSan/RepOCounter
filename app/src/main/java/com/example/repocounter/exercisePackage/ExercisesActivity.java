package com.example.repocounter.exercisePackage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

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
import com.example.repocounter.workoutsPackage.ChooseExToWorkoutListAdapter;

import java.util.Objects;

public class ExercisesActivity extends AppCompatActivity {

    private Storage storage;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exercises);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        storage = Storage.getInstance();
        storage.loadExercisesFromFile(getApplicationContext());
        storage.sortExercisesByType();

        recyclerView = findViewById(R.id.exercisesRecyclerView);


        if(Objects.equals(getIntent().getStringExtra("key"), "addExerciseToWorkout")){
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new ChooseExToWorkoutListAdapter(storage.getExerciseArrayList(), getApplicationContext()));


        }else{
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new ExercisesListAdapter(storage.getExerciseArrayList(), getApplicationContext()));

            Button addExerciseButton = findViewById(R.id.addExerciseButton);

            addExerciseButton.setOnClickListener(view -> {
                Intent intent = new Intent(getApplicationContext(), ExerciseEditActivity.class);
                intent.putExtra("key", "new");
                startActivity(intent);
            });

            Button backButton = findViewById(R.id.exercisesBackButton);
            backButton.setOnClickListener(view -> {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            });
        }

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Clear the activity stack
        startActivity(intent);
        finish(); // Finish the current activity
    }



}