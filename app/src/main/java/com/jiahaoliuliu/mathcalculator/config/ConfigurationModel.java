package com.jiahaoliuliu.mathcalculator.config;

import com.google.gson.Gson;

public class ConfigurationModel {

    // The init value for the number of exercises is 5
    private int numberOfExercises = 5;
    private boolean additionAllowed = true;
    private int maximumAdditionNumber = 100; // 100 is the init value

    // Let gson ignore itself on serialization
    private transient Gson gson;

    public ConfigurationModel() {
        gson = new Gson();
    }

    public ConfigurationModel(int numberOfExercises, boolean additionAllowed, int maximumAdditionNumber) {
        this.numberOfExercises = numberOfExercises;
        this.additionAllowed = additionAllowed;
        this.maximumAdditionNumber = maximumAdditionNumber;
        this.gson = new Gson();
    }

    public ConfigurationModel(String json) {
        gson = new Gson();
        ConfigurationModel configurationModel = gson.fromJson(json, ConfigurationModel.class);

        // Copy all the fields
        this.numberOfExercises = configurationModel.numberOfExercises;
        this.additionAllowed = configurationModel.additionAllowed;
        this.maximumAdditionNumber = configurationModel.maximumAdditionNumber;
    }

    public int getNumberOfExercises() {
        return numberOfExercises;
    }

    public void setNumberOfExercises(int numberOfExercises) {
        this.numberOfExercises = numberOfExercises;
    }

    public boolean isAdditionAllowed() {
        return additionAllowed;
    }

    public void setAdditionAllowed(boolean additionAllowed) {
        this.additionAllowed = additionAllowed;
    }

    public int getMaximumAdditionNumber() {
        return maximumAdditionNumber;
    }

    public void setMaximumAdditionNumber(int maximumAdditionNumber) {
        this.maximumAdditionNumber = maximumAdditionNumber;
    }

    public String toJson() {
        return gson.toJson(this);
    }
}
