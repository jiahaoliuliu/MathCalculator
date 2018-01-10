package com.jiahaoliuliu.mathcalculator.config;

import com.google.gson.Gson;

/**
 * Created by jiahaoliuliu on 1/10/18.
 */

public class ConfigurationModel {

    // The init value for the number of exercises is 5
    private int numberOfExercises = 5;

    // Let gson ignore itself on serialization
    private transient Gson gson;

    public ConfigurationModel() {
        gson = new Gson();
    }

    public ConfigurationModel(int numberOfExercises) {
        gson = new Gson();
        this.numberOfExercises = numberOfExercises;
    }

    public ConfigurationModel(String json) {
        gson = new Gson();
        ConfigurationModel configurationModel = gson.fromJson(json, ConfigurationModel.class);

        // Copy all the fields
        this.numberOfExercises = configurationModel.numberOfExercises;
    }

    public int getNumberOfExercises() {
        return numberOfExercises;
    }

    public void setNumberOfExercises(int numberOfExercises) {
        this.numberOfExercises = numberOfExercises;
    }

    public String toJson() {
        return gson.toJson(this);
    }
}
