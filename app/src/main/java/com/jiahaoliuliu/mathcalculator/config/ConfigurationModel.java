package com.jiahaoliuliu.mathcalculator.config;

/**
 * Created by jiahaoliuliu on 1/10/18.
 */

public class ConfigurationModel {

    // The init value for the number of exercises is 5
    private int numberOfExercises = 5;

    public ConfigurationModel() {
    }

    public ConfigurationModel(int numberOfExercises) {
        this.numberOfExercises = numberOfExercises;
    }

    public int getNumberOfExercises() {
        return numberOfExercises;
    }

    public void setNumberOfExercises(int numberOfExercises) {
        this.numberOfExercises = numberOfExercises;
    }
}
