package com.wouek.back.entity;

public class Result {
    public int[][] sudoku;
    public int backed;
    public int visited;
    public double time;

    public Result(int[][] sudoku, int backed, int visited, double time) {
        this.sudoku = sudoku;
        this.backed = backed;
        this.visited = visited;
        this.time = time;
    }
}
