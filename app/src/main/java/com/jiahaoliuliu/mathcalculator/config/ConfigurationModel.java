package com.jiahaoliuliu.mathcalculator.config;

/**
 * Created by jiahaoliuliu on 1/10/18.
 */

public class ConfigurationModel {

    private int numberOfExercises;

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
