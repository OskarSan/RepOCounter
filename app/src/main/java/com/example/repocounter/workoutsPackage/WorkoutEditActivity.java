package com.example.repocounter.workoutsPackage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repocounter.R;
import com.example.repocounter.Storage;
import com.example.repocounter.exercisePackage.ExercisesActivity;
import com.example.repocounter.exercisePackage.ExercisesListAdapter;

import java.util.ArrayList;
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
        Button confirmButton = findViewById(R.id.workoutEditConfirmButton);
        Button addExerciseToWorkoutButton = findViewById(R.id.addExerciseToWorkoutButton);

        if(Objects.equals(getIntent().getStringExtra("key"), "new")){
            workout = new Workout("new workout",new ArrayList<>());

        }else{
            workout = getIntent().getSerializableExtra("workout") != null ? (Workout) getIntent().getSerializableExtra("workout") : null;
        }

        recyclerView = findViewById(R.id.exerciseInWorkoutRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ExerciseInWorkoutListAdapter(/*change to workouts list*/workout.getExercises(), getApplicationContext()));




        if(Objects.equals(getIntent().getStringExtra("key"), "new")){
            titleText.setText("Create new workout");
            workoutNameEditText.setHint("Workout name");

        }else{
            titleText.setText("Edit workout");
            workoutNameEditText.setText(workout.getWorkoutName());

        }


        confirmButton.setOnClickListener(view -> {
            workout.setWorkoutName(workoutNameEditText.getText().toString());


            if(Objects.equals(getIntent().getStringExtra("key"), "new")){
                storage.addWorkout(workout);
            }else{
                storage.editWorkout(workout, workout.getWorkoutID());
            }
            Storage.getInstance().saveWorkoutsToFile(getApplicationContext());
            Storage.getInstance().loadWorkoutsFromFile(getApplicationContext());

            Intent intent = new Intent(getApplicationContext(), WorkoutsActivity.class);
            startActivity(intent);
            finish();


        });

        addExerciseToWorkoutButton.setOnClickListener(view -> {

            Intent intent = new Intent(getApplicationContext(), ExercisesActivity.class);
            intent.putExtra("key", "addExerciseToWorkout");
            startActivity(intent);

        });





    }
}