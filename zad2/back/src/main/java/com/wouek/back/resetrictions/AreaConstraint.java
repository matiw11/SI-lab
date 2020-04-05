package com.wouek.back.resetrictions;

import com.wouek.back.entity.Sudoku;
import com.wouek.back.entity.SudokuVariable;

public class AreaConstraint extends Constraint {
    @Override
    public SudokuVariable[] getScope(SudokuVariable variable, Sudoku sudoku) {
        int squareRow = variable.row/3;
        int squareColumn = variable.column/3;
        SudokuVariable[] squareVars = new SudokuVariable[9];
        int counter = 0;
        for (int i =squareRow*3; i<squareRow*3+3; i++){
            for (int j =squareColumn*3; j<squareColumn*3+3; j++){
                squareVars[counter] = sudoku.puzzle[i][j];
                counter++;
            }
        }
        return squareVars;
    }
}
