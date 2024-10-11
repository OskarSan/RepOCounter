package com.example.repocounter.exercisePackage;

import com.example.repocounter.R;

import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.UUID;

public class Exercise implements Serializable {
    protected ExerciseType exerciseType;
    protected String exerciseName, exerciseDescription, exerciseID, notes;
    protected Integer reps, weight, setAmount;
    protected ArrayList<Set> setList;
    //kuvasetti
    protected int image = R.drawable.ic_launcher_foreground;

    public Exercise ( ExerciseType type, String name, String description, Integer reps, Integer weight){
        this.exerciseID = UUID.randomUUID().toString();
        this.exerciseType = type;
        this.exerciseName = name;
        this.exerciseDescription = description;
        this.reps = reps;
        this.weight = weight;
        this.setAmount = 0;
        this.setList = new ArrayList<>();
        this.notes = "";
    }


    public String getExerciseName(){return exerciseName;}
    public ExerciseType getExerciseType(){return exerciseType;}
    public String getExerciseID(){return exerciseID;}
    public Integer getWeight(){return weight;}
    public String getExerciseDescription(){return exerciseDescription;}
    public Integer getReps(){return reps;}
    public Integer getSetAmount(){return setAmount;}
    public ArrayList<Set> getSetList(){return setList;}
    public String getNotes(){return notes;}

    public void setWeight(int weight){
      this.weight = weight;
    };

    public void setReps(int reps){
        this.reps = reps;
    };

    public void setnotes(String notes){
        this.notes = notes;
    }

    public void setExerciseID(String exerciseID) {
        this.exerciseID = exerciseID;
    }
    public void setExerciseSets(Integer sets){
        this.setAmount = sets;
        for (int i = 0; i < sets; i++) {
            setList.add(new Set(0,0,null)); // Add a new MyObject instance to the list
        }
    }
    public void addSet(Set set){
        setList.add(set);
        setAmount++;
    }
    public void removeSet(Set set){
        setList.remove(set);
        setAmount--;
    }

    public void setSetList(ArrayList<Set> setList){
        this.setList = setList;
    }




}
