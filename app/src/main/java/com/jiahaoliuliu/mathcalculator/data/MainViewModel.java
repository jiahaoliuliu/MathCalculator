package com.jiahaoliuliu.mathcalculator.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class MainViewModel {
    private static final int MAX_LIST_SIZE = 5;
    private static final int MAXIMUM_NUMBER = 100;

    private List<MathOperationModel> mathOperationModelsCollection;
    private Iterator<MathOperationModel> collectionIterator;

    // Singleton
    private static MainViewModel mainViewModel;

    private MainViewModel() {
        // init the variable
        mathOperationModelsCollection = new ArrayList<>();
    }

    public static MainViewModel getInstance() {
        if (mainViewModel == null) {
            mainViewModel = new MainViewModel();
        }

        return mainViewModel;
    }

    public void generateNewMathOperationModelsList(int listSize) {
        int finalLisSize = listSize;
        if (listSize > MAX_LIST_SIZE) {
            finalLisSize = MAX_LIST_SIZE;
        }

        // Restart the content of the list
        mathOperationModelsCollection.clear();
        collectionIterator = null;

        for (int i = 0; i < finalLisSize; i++) {
            MathOperationModel mathOperationModel = generateMathOperationModel();
            // if it is the last number
            if (i == finalLisSize - 1) {
                mathOperationModel.setLastOperation(true);
            }
            mathOperationModelsCollection.add(mathOperationModel);
        }

        collectionIterator = mathOperationModelsCollection.iterator();
    }

    /**
     * Get the next math operation to be done
     *
     * @return
     *      The next math operation
     */
    public MathOperationModel getNextMathOperationModel() {
        if (collectionIterator != null && collectionIterator.hasNext()) {
            return collectionIterator.next();
        }

        return null;
    }

    /**
     * Get the list of results
     * @return
     *      The list of results
     */
    public List<MathOperationModel> getResultsList() {
        return mathOperationModelsCollection;
    }

    private MathOperationModel generateMathOperationModel() {
        Random random = new Random();
        int firstNumber = random.nextInt(MAXIMUM_NUMBER);
        int secondNumber = random.nextInt(MAXIMUM_NUMBER);
        MathOperationModel.Operation mathOperation =
            MathOperationModel.Operation.retrieveOperation(
                    random.nextInt(MathOperationModel.Operation.values().length));

        // For now the negative number on extraction is not allowed. The new number i
        if (mathOperation == MathOperationModel.Operation.EXTRACTION && secondNumber < firstNumber) {
            secondNumber = random.nextInt((MAXIMUM_NUMBER - firstNumber) + 1) + firstNumber;
        }
        int correctResult = mathOperation.operate(firstNumber, secondNumber);

        return new MathOperationModel(firstNumber, mathOperation, secondNumber,
                correctResult, false);
    }
}
