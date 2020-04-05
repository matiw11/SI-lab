package com.wouek.back.resetrictions;


import com.wouek.back.entity.Sudoku;
import com.wouek.back.entity.SudokuVariable;

public abstract class Constraint {
    public boolean isOK(SudokuVariable variable, Sudoku sudoku) {
        for (SudokuVariable var: getScope(variable, sudoku)){
            if (!var.equals(variable)){
                if(var.value!=0){
                    if (var.value == variable.value){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public abstract SudokuVariable[] getScope(SudokuVariable variable, Sudoku sudoku);
}
