package com.jiahaoliuliu.mathcalculator.calculate;

public class TotalTimer {

    private int minutes;
    private int seconds;

    public TotalTimer(int minutes, int seconds) {
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TotalTimer that = (TotalTimer) o;

        if (minutes != that.minutes) return false;
        return seconds == that.seconds;
    }

    @Override
    public int hashCode() {
        int result = minutes;
        result = 31 * result + seconds;
        return result;
    }

    @Override
    public String toString() {
        return "TotalTimer{" +
                "minutes=" + minutes +
                ", seconds=" + seconds +
                '}';
    }
}
