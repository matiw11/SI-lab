package com.wouek.backendv2.domain;

public class Result {

    private int [][] sudoku;
    private double firstTime;
    private int firstVisited;
    private int firstBacked;
    private double allTime;
    private int allVisited;
    private int allBacked;
    private  int solutionsCount;

    public Result(int[][] sudoku, double firstTime, int firstVisited, int firstBacked, double allTime, int allVisited, int allBacked, int solutionsCount) {
        this.sudoku = sudoku;
        this.firstTime = firstTime;
        this.firstVisited = firstVisited;
        this.firstBacked = firstBacked;
        this.allTime = allTime;
        this.allVisited = allVisited;
        this.allBacked = allBacked;
        this.solutionsCount = solutionsCount;
    }

    public void setFirstTime(double firstTime) {
        this.firstTime = firstTime;
    }

    public void setAllTime(double allTime) {
        this.allTime = allTime;
    }

    public int getSolutionsCount() {
        return solutionsCount;
    }

    public void setSolutionsCount(int solutionsCount) {
        this.solutionsCount = solutionsCount;
    }

    public int[][] getSudoku() {
        return sudoku;
    }

    public void setSudoku(int[][] sudoku) {
        this.sudoku = sudoku;
    }

    public double getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(int firstTime) {
        this.firstTime = firstTime;
    }

    public int getFirstVisited() {
        return firstVisited;
    }

    public void setFirstVisited(int firstVisited) {
        this.firstVisited = firstVisited;
    }

    public int getFirstBacked() {
        return firstBacked;
    }

    public void setFirstBacked(int firstBacked) {
        this.firstBacked = firstBacked;
    }

    public double getAllTime() {
        return allTime;
    }

    public void setAllTime(int allTime) {
        this.allTime = allTime;
    }

    public int getAllVisited() {
        return allVisited;
    }

    public void setAllVisited(int allVisited) {
        this.allVisited = allVisited;
    }

    public int getAllBacked() {
        return allBacked;
    }

    public void setAllBacked(int allBacked) {
        this.allBacked = allBacked;
    }

    @Override
    public String toString() {
        return "Result{" +
                "firstTime=" + firstTime +
                ", firstVisited=" + firstVisited +
                ", firstBacked=" + firstBacked +
                ", allTime=" + allTime +
                ", allVisited=" + allVisited +
                ", allBacked=" + allBacked +
                ", solutionsCount=" + solutionsCount +
                '}';
    }
}
