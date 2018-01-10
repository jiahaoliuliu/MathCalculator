package com.jiahaoliuliu.mathcalculator.data;

/**
 * General result to be shown on the final list
 */
public class GeneralResultModel {

    private int numberOfCorrectResults;
    private int numberOfWrongResults;
    private int points = -1;

    public GeneralResultModel(int numberOfCorrectResults, int numberOfWrongResults) {
        this.numberOfCorrectResults = numberOfCorrectResults;
        this.numberOfWrongResults = numberOfWrongResults;
    }

    public int getNumberOfCorrectResults() {
        return numberOfCorrectResults;
    }

    public void setNumberOfCorrectResults(int numberOfCorrectResults) {
        this.numberOfCorrectResults = numberOfCorrectResults;
    }

    public int getNumberOfWrongResults() {
        return numberOfWrongResults;
    }

    public void setNumberOfWrongResults(int numberOfWrongResults) {
        this.numberOfWrongResults = numberOfWrongResults;
    }

    public int getPoints() {
        // If the points was not set, set it
        if (points == -1) {
            setPoints();
        }

        return points;
    }

    // The points cannot be set outside
    private void setPoints() {
        int totalNumberOfResults = getTotalNumberOfResults();
        // There is not points when the number of results is zero
        if (totalNumberOfResults == 0) {
            this.points = 0;
            return;
        }

        this.points = (int) ((double)numberOfCorrectResults / totalNumberOfResults * 100.0);
    }

    public int getTotalNumberOfResults() {
        return numberOfCorrectResults + numberOfWrongResults;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GeneralResultModel that = (GeneralResultModel) o;

        if (numberOfCorrectResults != that.numberOfCorrectResults) return false;
        if (numberOfWrongResults != that.numberOfWrongResults) return false;
        return points == that.points;
    }

    @Override
    public int hashCode() {
        int result = numberOfCorrectResults;
        result = 31 * result + numberOfWrongResults;
        result = 31 * result + points;
        return result;
    }

    @Override
    public String toString() {
        return "GeneralResultModel{" +
                "totalNumberOfResults=" + getTotalNumberOfResults() +
                ", numberOfCorrectResults=" + numberOfCorrectResults +
                ", numberOfWrongResults=" + numberOfWrongResults +
                ", points=" + getPoints() +
                '}';
    }
}
