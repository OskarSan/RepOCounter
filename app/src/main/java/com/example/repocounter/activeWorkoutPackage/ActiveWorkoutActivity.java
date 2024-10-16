package com.example.repocounter.activeWorkoutPackage;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repocounter.R;
import com.example.repocounter.Storage;
import com.example.repocounter.statisticsPackage.WorkoutLogEntry;
import com.example.repocounter.workoutsPackage.Workout;

public class ActiveWorkoutActivity extends AppCompatActivity {

    private Workout workout;
    private RecyclerView recyclerView;
    private TextView workoutNameTextView;
    private Button finishWorkoutButton;
    private WorkoutLogEntry workoutLogEntry;
    private int tapCount = 0;

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
        recyclerView.setAdapter(new ActiveWorkoutListAdapter(getApplicationContext(), workout.getExerciseArrayList(), workout));

        finishWorkoutButton = findViewById(R.id.finishWorkoutButton);

        finishWorkoutButton.setOnClickListener(view -> {
            tapCount++;
            Handler handler = new Handler(Looper.getMainLooper());

            // Create a Runnable for the delayed action (Toast)
            Runnable showToastRunnable = () -> {
                if (tapCount == 1) { // Only show if it's still a single tap
                    Toast.makeText(this, "double-tap to Finish workout", Toast.LENGTH_SHORT).show();
                }
                tapCount = 0; // Reset tap count after showing the message
            };

            if (tapCount == 2) {
                // Cancel the pending Toast if the second tap happened
                handler.removeCallbacks(showToastRunnable);

                // Proceed to finish the workout
                workoutLogEntry = new WorkoutLogEntry(workout);
                System.out.println("ekan exercises weight = " + workoutLogEntry.getWorkout().getExerciseArrayList().get(0).getWeight());
                Storage.getInstance().addWorkoutLogEntry(workoutLogEntry);
                Storage.getInstance().saveWorkoutLogToFile(getApplicationContext());

                // Finish the activity and show the completion Toast
                finish();
                Toast.makeText(this, "Workout finished!", Toast.LENGTH_SHORT).show();

                tapCount = 0; // Reset tap count after finishing
            } else if (tapCount == 1) {
                // Post the Runnable with a delay
                handler.postDelayed(showToastRunnable, 200); // 500 milliseconds delay
            }
        });



    }
}