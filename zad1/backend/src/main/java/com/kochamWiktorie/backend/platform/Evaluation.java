package com.kochamWiktorie.backend.platform;

public class Evaluation {
    private double max;
    private double average;
    private double min;

    public Evaluation(double max, double average, double min) {
        this.max = max;
        this.average = average;
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public double getAverage() {
        return average;
    }

    public double getMin() {
        return min;
    }

    @Override
    public String toString() {
        return "Evaluation{" +
                "max=" + max +
                ", average=" + average +
                ", min=" + min +
                '}';
    }
}
