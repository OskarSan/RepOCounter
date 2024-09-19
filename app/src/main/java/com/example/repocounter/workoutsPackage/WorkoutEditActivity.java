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
import com.example.repocounter.exercisePackage.Exercise;
import com.example.repocounter.exercisePackage.ExercisesActivity;

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


        //TODO: FIX THIS SHIT
        if(Objects.equals(getIntent().getStringExtra("key"), "new")){
            workout = new Workout("new workout",new ArrayList<>());
            System.out.println("tän koko " + workout.exercises.size());
            titleText.setText("Create new workout");
            workoutNameEditText.setHint("Workout name");
            storage.setWorkoutCarrier(workout);

        /*} else if (getIntent().getSerializableExtra("key") instanceof Exercise) {
            workout = storage.getWorkoutCarrier();
            System.out.println("tää tulee ny");
            System.out.println(workout.getWorkoutName() +" "+ workout.exercises.size());

            workout.getExercises().add((Exercise) getIntent().getSerializableExtra("key"));
        */
        } else{
            if(storage.getWorkoutCarrier() != null){
                workout = storage.getWorkoutCarrier();
            }else{
                workout = getIntent().getSerializableExtra("workout") != null ? (Workout) getIntent().getSerializableExtra("workout") : null;

            }
            if(storage.getExerciseCarrier() != null){
                workout.addExercise(storage.getExerciseCarrier());
                storage.setExerciseCarrier(null);
            }
            storage.setWorkoutCarrier(workout);
            titleText.setText("Edit workout");
            workoutNameEditText.setText(workout.getWorkoutName());
        }

        recyclerView = findViewById(R.id.exercisesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ExerciseInWorkoutListAdapter(/*change to workouts list*/workout.getExercises(), getApplicationContext()));




        confirmButton.setOnClickListener(view -> {
            workout.setWorkoutName(workoutNameEditText.getText().toString());

            boolean workoutExists = storage.getWorkoutArrayList().stream()
                    .anyMatch(w -> Objects.equals(w.getWorkoutID(), workout.getWorkoutID()));

            if (workoutExists) {
                storage.editWorkout(workout, workout.getWorkoutID());
            } else {
                storage.addWorkout(workout);
            }

            Storage.getInstance().saveWorkoutsToFile(getApplicationContext());
            Storage.getInstance().loadWorkoutsFromFile(getApplicationContext());
            storage.setExerciseCarrier(null);
            storage.setWorkoutCarrier(null);
            Intent intent = new Intent(getApplicationContext(), WorkoutsActivity.class);
            startActivity(intent);
            finish();


        });

        addExerciseToWorkoutButton.setOnClickListener(view -> {
            workout.setWorkoutName(workoutNameEditText.getText().toString());
            workout.setExercises(workout.getExercises());
            System.out.println(workout.getWorkoutName());
            storage.setWorkoutCarrier(workout);
            Intent intent = new Intent(getApplicationContext(), ExercisesActivity.class);
            intent.putExtra("key", "addExerciseToWorkout");
            startActivity(intent);

        });





    }
}