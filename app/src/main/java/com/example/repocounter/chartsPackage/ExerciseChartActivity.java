package com.example.repocounter.chartsPackage;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.repocounter.R;
import com.example.repocounter.Storage;
import com.example.repocounter.exercisePackage.Exercise;
import com.example.repocounter.exercisePackage.Set;
import com.example.repocounter.statisticsPackage.WorkoutLogEntry;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExerciseChartActivity extends AppCompatActivity {

    private Storage storage;
    private TextView exerciseTextView;
    private Exercise exercise;
    private GraphView graph;
    private HashMap<LocalDateTime, WorkoutLogEntry> workoutLog;
    private WorkoutLogEntry logEntry;
    private ArrayList<Exercise> exercises = new ArrayList<>();
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exercise_chart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        storage = Storage.getInstance();

        storage.loadWorkoutLogFromFile(getApplicationContext());
        workoutLog = storage.getWorkoutLog();

        backButton = findViewById(R.id.exerciseChartBackButton);
        backButton.setOnClickListener(view -> {
            finish();
        });

        exerciseTextView = findViewById(R.id.chartedExerciseTextView);
        exercise = (Exercise) getIntent().getSerializableExtra("key");
        exerciseTextView.setText(exercise.getExerciseName());

        ArrayList<Integer> exerciseDataList = new ArrayList<>();

        for (Map.Entry<LocalDateTime, WorkoutLogEntry> entry : workoutLog.entrySet()) {
            LocalDateTime date = entry.getKey();
            WorkoutLogEntry logEntry = entry.getValue();

            exercises = logEntry.getWorkout().getExerciseArrayList();
            for(Exercise ex : exercises){
                if(ex.getExerciseName().equals(exercise.getExerciseName())){

                    for(Set set : ex.getSetList()){
                        exerciseDataList.add(set.getWeight());
                        System.out.println("weight = "+set.getWeight());
                        System.out.println("reps = "+set.getReps());
                        System.out.println("\n");
                    }

                }
            }
        }
        graph = (GraphView) findViewById(R.id.graph);


        DataPoint[] dataPoints = new DataPoint[exerciseDataList.size()];
        for (int i = 0; i < exerciseDataList.size(); i++) {
            dataPoints[i] = new DataPoint(i, exerciseDataList.get(i));
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints);

        // after adding data to our line graph series.
        // on below line we are setting
        // title for our graph view.
        graph.setTitle("My Graph View");

        // on below line we are setting
        // text color to our graph view.
        graph.setTitleColor(R.color.black);

        // on below line we are setting
        // our title text size.
        graph.setTitleTextSize(60);

        // on below line we are adding
        // data series to our graph view.
        graph.addSeries(series);
        // graph code is fetched from https://www.geeksforgeeks.org/line-graph-view-in-android-with-example/
    }
}