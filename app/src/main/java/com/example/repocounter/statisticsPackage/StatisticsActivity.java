package com.example.repocounter.statisticsPackage;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.repocounter.R;
import com.example.repocounter.Storage;
import com.example.repocounter.exercisePackage.Exercise;
import com.example.repocounter.exercisePackage.Set;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class StatisticsActivity extends AppCompatActivity {



    private HashMap<LocalDateTime, WorkoutLogEntry> workoutLog;
    private CalendarView logCalendarView;
    private Calendar calendar;
    private Button backButton;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_statistics);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Storage.getInstance().loadWorkoutLogFromFile(this);

        workoutLog = Storage.getInstance().getWorkoutLog();


        backButton = findViewById(R.id.LogBackButton);
        logCalendarView = findViewById(R.id.logCalendarView);
        calendar = calendar.getInstance();
        long currentMillis = calendar.getTimeInMillis();

        logCalendarView.setDate(currentMillis);



        logCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                String dateString = String.format("%d/%d/%d", dayOfMonth, month + 1, year);
                //Toast.makeText(StatisticsActivity.this, dateString, Toast.LENGTH_SHORT).show();

                LocalDate selectedDate = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O    ) {
                    selectedDate = LocalDate.of(year, month+1, dayOfMonth);
                }
                WorkoutLogEntry logEntry = workoutLog.get(selectedDate);

                if (logEntry != null) {
                    Dialog dialog = new Dialog(StatisticsActivity.this);
                    dialog.setContentView(R.layout.workout_details_dialog);

                    TextView workoutNameTextView = dialog.findViewById(R.id.logWorkoutNameTV);

                    TextView workoutExercisesTV = dialog.findViewById(R.id.logWorkoutExercisesTV);

                    ArrayList<Exercise> exerciseArrayList = logEntry.getWorkout().getExerciseArrayList();

                    StringBuilder exerciseString = new StringBuilder();
                    for (Exercise exercise : exerciseArrayList) {

                        ArrayList<Set> sets = exercise.getSetList();

                        exerciseString
                                .append(exercise.getExerciseName())
                                .append(": \n");

                        // Add a newline after each exercise
                        for(Set set : sets){
                            exerciseString
                                    .append(set.getWeight())
                                    .append("kg, ")
                                    .append(set.getReps())
                                    .append(" Reps\n");
                        }
                        exerciseString.append("\n");

                    }
                    String allExercises = exerciseString.toString();
                    workoutNameTextView.setText(logEntry.getWorkout().getWorkoutName());
                    workoutExercisesTV.setText(allExercises);

                    dialog.show();

                } else {
                    // No WorkoutLogEntry found for the selected date
                    // ...
                    System.out.println("no workout");
                }


            }
        });

        backButton.setOnClickListener(view -> {
            finish();
        });

    }


    /*
    public void getDate(){
        long date = logCalendarView.getDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy", Locale.getDefault());
        calendar.setTimeInMillis(date);
        String selectedDate = simpleDateFormat.format(calendar.getTime());
        Toast.makeText(this, selectedDate, Toast.LENGTH_SHORT).show();
    }


    public void setDate(int day, int month, int year){
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        long milli = calendar.getTimeInMillis();
        logCalendarView.setDate(milli);
    }
    */
}