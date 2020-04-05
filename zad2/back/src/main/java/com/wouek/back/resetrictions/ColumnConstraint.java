package com.wouek.back.resetrictions;


import com.wouek.back.entity.Sudoku;
import com.wouek.back.entity.SudokuVariable;

public class ColumnConstraint extends Constraint {


    @Override
    public SudokuVariable[] getScope(SudokuVariable variable, Sudoku sudoku) {
        SudokuVariable[] column = new SudokuVariable[9];
        for (int i=0; i<9; i++){
            column[i] = sudoku.puzzle[i][variable.column];
        }
        return column;
    }
}
