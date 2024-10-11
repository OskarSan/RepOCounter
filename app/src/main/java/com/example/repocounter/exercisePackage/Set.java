package com.example.repocounter.exercisePackage;

import java.io.Serializable;

public class Set implements Serializable {

    protected Integer weight, reps;
    protected String notes;

    public Set(Integer weight, Integer reps, String notes){
        this.weight = weight;
        this.reps = reps;
        this.notes = notes;
    }


    public Integer getWeight(){return weight;}
    public Integer getReps(){return reps;}
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

}
