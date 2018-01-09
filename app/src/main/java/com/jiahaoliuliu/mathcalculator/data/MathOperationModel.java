package com.jiahaoliuliu.mathcalculator.data;

public class MathOperationModel {

    public enum Operation {
        ADDITION("+") {
            @Override
            int operate(int firstNumber, int secondNumber) {
                return firstNumber + secondNumber;
            }
        }, EXTRACTION("-") {
            @Override
            int operate(int firstNumber, int secondNumber) {
                return firstNumber - secondNumber;
            }
        };
//        , MULTIPLICATION, DIVISION

        private String symbol;


        Operation(String symbol) {
            this.symbol = symbol;
        }

        public String getSymbol() {
            return symbol;
        }

        abstract int operate(int firstNumber, int secondNumber);

        // Static method
        public static Operation retrieveOperation(int order) {
            // TODO: implement this
            return ADDITION;
        }
    }

    private int firstNumber;

    private Operation operation;

    private int secondNumber;

    private int correctResult;

    // The result given by the input
    private int givenResult;

    private boolean isLastOperation = false;

    public MathOperationModel(int firstNumber, Operation operation, int secondNumber,
                              int correctResult, boolean isLastOperation) {
        this.firstNumber = firstNumber;
        this.operation = operation;
        this.secondNumber = secondNumber;
        this.correctResult = correctResult;
        this.isLastOperation = isLastOperation;
    }

    public int getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public int getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(int secondNumber) {
        this.secondNumber = secondNumber;
    }

    public boolean isLastOperation() {
        return isLastOperation;
    }

    public void setLastOperation(boolean lastOperation) {
        isLastOperation = lastOperation;
    }

    public int getCorrectResult() {
        return correctResult;
    }

    public void setCorrectResult(int correctResult) {
        this.correctResult = correctResult;
    }

    public int getGivenResult() {
        return givenResult;
    }

    public void setGivenResult(int givenResult) {
        this.givenResult = givenResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MathOperationModel that = (MathOperationModel) o;

        if (firstNumber != that.firstNumber) return false;
        if (secondNumber != that.secondNumber) return false;
        if (correctResult != that.correctResult) return false;
        if (givenResult != that.givenResult) return false;
        if (isLastOperation != that.isLastOperation) return false;
        return operation == that.operation;
    }

    @Override
    public int hashCode() {
        int result = firstNumber;
        result = 31 * result + (operation != null ? operation.hashCode() : 0);
        result = 31 * result + secondNumber;
        result = 31 * result + correctResult;
        result = 31 * result + givenResult;
        result = 31 * result + (isLastOperation ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MathOperationModel{" +
                "firstNumber=" + firstNumber +
                ", operation=" + operation +
                ", secondNumber=" + secondNumber +
                ", correctResult=" + correctResult +
                ", givenResult=" + givenResult +
                ", isLastOperation=" + isLastOperation +
                '}';
    }
}
