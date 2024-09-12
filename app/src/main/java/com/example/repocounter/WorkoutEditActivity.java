package com.example.repocounter;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

public class WorkoutEditActivity extends AppCompatActivity {

    private Storage storage;
    private RecyclerView recyclerView;
    private Workout workout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_workout_edit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //declarations
        storage = Storage.getInstance();

        TextView titleText = findViewById(R.id.editWorkoutTitleTextView);
        EditText workoutNameEditText = findViewById(R.id.setWorkoutNameEditText);
        recyclerView = findViewById(R.id.exercisesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ExercisesListAdapter(/*change to workouts list*/storage.getExerciseArrayList(), getApplicationContext()));




        if(Objects.equals(getIntent().getStringExtra("key"), "new")){
            titleText.setText("Create new workout");
            workoutNameEditText.setHint("Workout name");

        }else{

        }




    }
}