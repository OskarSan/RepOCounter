package com.example.repocounter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class ExerciseEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exercise_edit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //declarations
        TextView titleText = findViewById(R.id.editCreateTextView);
        EditText exerciseNameEditText = findViewById(R.id.exerciseNameEditText);
        EditText exerciseDescriptionEditText = findViewById(R.id.exerciseDescriptionEditText);
        EditText editWeight = findViewById(R.id.editWeight);
        EditText editReps = findViewById(R.id.editReps);
        Button confirmButton = findViewById(R.id.confirmButton);


        //spinner
        Spinner spinner = findViewById(R.id.exerciseTypeSpinner);
        List<String> exerciseTypes = new ArrayList<>();
        for (ExerciseType type : ExerciseType.values()) {
            exerciseTypes.add(type.getDisplayName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, exerciseTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        if(getIntent().getStringExtra("key").equals("new")){
            titleText.setText("Create new exercise");
            exerciseNameEditText.setHint("Exercise name");
            exerciseDescriptionEditText.setHint("Exercise description");
            editWeight.setHint("Weight (kg)");
            editReps.setHint("Reps");

        }else{
            titleText.setText("Edit exercise");
        }

        confirmButton.setOnClickListener(view -> {

            ExerciseType type = ExerciseType.valueOf(spinner.getSelectedItem().toString().toUpperCase());
            System.out.println(type);
            String name = exerciseNameEditText.getText().toString();
            String description = exerciseDescriptionEditText.getText().toString();
            Integer weight = Integer.parseInt(editWeight.getText().toString());
            Integer reps = Integer.parseInt(editReps.getText().toString());

            if(getIntent().getStringExtra("key").equals("new")){
                Storage.getInstance().addExercise(new Exercise(type, name, description, weight, reps));
            }else{
                Storage.getInstance().editExercise(new Exercise(type, name, description, weight, reps));
            }

            Intent intent = new Intent(getApplicationContext(), ExercisesActivity.class);
            startActivity(intent);
            finish();
        });




    }
}