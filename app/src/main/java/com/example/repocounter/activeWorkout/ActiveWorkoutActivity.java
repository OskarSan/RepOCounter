package com.example.repocounter.activeWorkout;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repocounter.R;
import com.example.repocounter.workoutsPackage.Workout;

public class ActiveWorkoutActivity extends AppCompatActivity {

    private Workout workout;
    private RecyclerView recyclerView;
    private TextView workoutNameTextView;
    private Button finishWorkoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_active_workout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        workout = (Workout) getIntent().getSerializableExtra("workout");
        workoutNameTextView = findViewById(R.id.activeWorkoutNameTextView);
        workoutNameTextView.setText(workout.getWorkoutName());

        recyclerView = findViewById(R.id.activeWorkoutRV);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ActiveWorkoutListAdapter(getApplicationContext(), workout.getExerciseArrayList()));




    }
}