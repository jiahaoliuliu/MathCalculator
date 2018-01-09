package com.jiahaoliuliu.mathcalculator.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class MainViewModel {
    private static final boolean ALLOW_NEGATIVE_NUMBER_ON_EXTRACTION = false;
    private static final int MAXIMUM_NUMBER_ON_ADDITION = 100;
    private static final int MAXIMUM_NUMBER_ON_MULTIPLICATION = 10;

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
        // Restart the content of the list
        mathOperationModelsCollection.clear();
        collectionIterator = null;

        for (int i = 0; i < listSize; i++) {
            MathOperationModel mathOperationModel = generateMathOperationModel();

            // Avoid duplications
            while(mathOperationModelsCollection.contains(mathOperationModel)) {
                mathOperationModel = generateMathOperationModel();
            }

            // if it is the last number
            if (i == listSize - 1) {
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
        MathOperationModel.Operation mathOperation =
            MathOperationModel.Operation.retrieveOperation(
                    random.nextInt(MathOperationModel.Operation.values().length));
        int firstNumber = 0;
        int secondNumber = 0;
        switch (mathOperation) {
            case ADDITION:
                firstNumber = random.nextInt(MAXIMUM_NUMBER_ON_ADDITION);
                secondNumber = random.nextInt(MAXIMUM_NUMBER_ON_ADDITION);
                // Not exceed the maximum number
                if (mathOperation.operate(firstNumber, secondNumber) > MAXIMUM_NUMBER_ON_ADDITION) {
                    // Generate a new number from the first number
                    int result = random.nextInt(
                            MAXIMUM_NUMBER_ON_ADDITION - firstNumber + 1 ) + firstNumber;
                    secondNumber = result - firstNumber;
                }
                break;
            case EXTRACTION:
                firstNumber = random.nextInt(MAXIMUM_NUMBER_ON_ADDITION);
                secondNumber = random.nextInt(MAXIMUM_NUMBER_ON_ADDITION);
                // Negative number on extraction
                if (!ALLOW_NEGATIVE_NUMBER_ON_EXTRACTION) {
                    // For now the negative number on extraction is not allowed. The new number i
                    while ( secondNumber > firstNumber) {
                        secondNumber = random.nextInt(firstNumber);
                    }
                }
                break;
            case MULTIPLICATION:
                firstNumber = random.nextInt(MAXIMUM_NUMBER_ON_MULTIPLICATION);
                secondNumber = random.nextInt(MAXIMUM_NUMBER_ON_MULTIPLICATION);
                // There is not other restriction on multiplication
                break;
        }


        int correctResult = mathOperation.operate(firstNumber, secondNumber);

        return new MathOperationModel(firstNumber, mathOperation, secondNumber,
                correctResult, false);
    }
}
