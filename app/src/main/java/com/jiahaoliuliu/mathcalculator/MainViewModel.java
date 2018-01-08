package com.jiahaoliuliu.mathcalculator;

import java.util.Random;
import java.util.Stack;

import com.jiahaoliuliu.mathcalculator.MathOperationModel.Operation;

public class MainViewModel {
    private static final int MAX_LIST_SIZE = 5;
    private static final int MAXIMUM_NUMBER = 100;

    private Stack<MathOperationModel> mathOperationModelsStack;

    // Singleton
    private static MainViewModel mainViewModel;

    private MainViewModel() {
        // init the variable
        mathOperationModelsStack = new Stack<>();
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
        mathOperationModelsStack.clear();

        for (int i = 0; i < finalLisSize; i++) {
            MathOperationModel mathOperationModel = generateMathOperationModel();
            // if it is the last number
            if (i == finalLisSize - 1) {
                mathOperationModel.setLastOperation(true);
            }
            mathOperationModelsStack.push(mathOperationModel);
        }
    }

    public MathOperationModel getNextMathOperationModel() {
        return mathOperationModelsStack.pop();
    }

    private MathOperationModel generateMathOperationModel() {
        Random random = new Random();
        int firstNumber = random.nextInt(MAXIMUM_NUMBER);
        int secondNumber = random.nextInt(MAXIMUM_NUMBER);
        MathOperationModel.Operation mathOperation =
            Operation.retrieveOperation(random.nextInt(Operation.values().length));

        // For now the negative number on extraction is not allowed. The new number i
        if (mathOperation == Operation.EXTRACTION && secondNumber < firstNumber) {
            secondNumber = random.nextInt((MAXIMUM_NUMBER - firstNumber) + 1) + firstNumber;
        }
        int correctResult = mathOperation.operate(firstNumber, secondNumber);

        return new MathOperationModel(firstNumber, mathOperation, secondNumber,
                correctResult, false);
    }
}
