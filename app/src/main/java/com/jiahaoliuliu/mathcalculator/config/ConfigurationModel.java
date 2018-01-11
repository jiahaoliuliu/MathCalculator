package com.jiahaoliuliu.mathcalculator.config;

import com.google.gson.Gson;

public class ConfigurationModel {

    // General settings
    // The init value for the number of exercises is 5
    private int numberOfExercises = 5;

    // Addition
    private boolean additionAllowed = true;
    private int maximumAdditionNumber = 100; // 100 is the init value

    // Extraction
    private boolean extractionAllowed = true;
    private int maximumExtractionNumber = 100; // 100 is the init value

    // Multiplication
    private boolean multiplicationAllowed = true;
    private int maximumMultiplicationNumber = 100; // 100 is the init value

    // Let gson ignore itself on serialization
    private transient Gson gson;

    public ConfigurationModel() {
        gson = new Gson();
    }

    public ConfigurationModel(int numberOfExercises, boolean additionAllowed,
                              int maximumAdditionNumber, boolean extractionAllowed,
                              int maximumExtractionNumber, boolean multiplicationAllowed,
                              int maximumMultiplicationNumber) {
        this.numberOfExercises = numberOfExercises;
        this.additionAllowed = additionAllowed;
        this.maximumAdditionNumber = maximumAdditionNumber;
        this.extractionAllowed = extractionAllowed;
        this.maximumExtractionNumber = maximumExtractionNumber;
        this.multiplicationAllowed = multiplicationAllowed;
        this.maximumMultiplicationNumber = maximumMultiplicationNumber;
    }

    public ConfigurationModel(String json) {
        gson = new Gson();
        ConfigurationModel configurationModel = gson.fromJson(json, ConfigurationModel.class);

        // Copy all the fields
        this.numberOfExercises = configurationModel.numberOfExercises;
        this.additionAllowed = configurationModel.additionAllowed;
        this.maximumAdditionNumber = configurationModel.maximumAdditionNumber;
        this.extractionAllowed = configurationModel.extractionAllowed;
        this.maximumExtractionNumber = configurationModel.maximumExtractionNumber;
        this.multiplicationAllowed = configurationModel.multiplicationAllowed;
        this.maximumMultiplicationNumber = configurationModel.maximumMultiplicationNumber;
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

    public boolean isExtractionAllowed() {
        return extractionAllowed;
    }

    public void setExtractionAllowed(boolean extractionAllowed) {
        this.extractionAllowed = extractionAllowed;
    }

    public int getMaximumExtractionNumber() {
        return maximumExtractionNumber;
    }

    public void setMaximumExtractionNumber(int maximumExtractionNumber) {
        this.maximumExtractionNumber = maximumExtractionNumber;
    }

    public boolean isMultiplicationAllowed() {
        return multiplicationAllowed;
    }

    public void setMultiplicationAllowed(boolean multiplicationAllowed) {
        this.multiplicationAllowed = multiplicationAllowed;
    }

    public int getMaximumMultiplicationNumber() {
        return maximumMultiplicationNumber;
    }

    public void setMaximumMultiplicationNumber(int maximumMultiplicationNumber) {
        this.maximumMultiplicationNumber = maximumMultiplicationNumber;
    }

    public String toJson() {
        return gson.toJson(this);
    }
}
