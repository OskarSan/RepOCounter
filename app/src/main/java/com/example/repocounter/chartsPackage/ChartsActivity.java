package com.example.repocounter.chartsPackage;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repocounter.R;
import com.example.repocounter.Storage;

public class ChartsActivity extends AppCompatActivity {


    private Storage storage;
    private RecyclerView chartsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_charts);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        storage = Storage.getInstance();
        storage.loadExercisesFromFile(getApplicationContext());
        storage.loadWorkoutsFromFile(getApplicationContext());

        chartsRecyclerView = findViewById(R.id.chartsRecyclerView);
        chartsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chartsRecyclerView.setAdapter(new chartableExerciseListAdapter(getApplicationContext(), storage.getExerciseArrayList(), storage.getWorkoutLog()));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Storage.getInstance().saveExercisesToFile(getApplicationContext());
    }

}