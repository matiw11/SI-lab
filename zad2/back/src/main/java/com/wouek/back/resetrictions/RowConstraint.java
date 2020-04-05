package com.wouek.back.resetrictions;


import com.wouek.back.entity.Sudoku;
import com.wouek.back.entity.SudokuVariable;

public class RowConstraint extends Constraint {

    @Override
    public SudokuVariable[] getScope(SudokuVariable variable, Sudoku sudoku) {
        return sudoku.puzzle[variable.row];
    }

}
