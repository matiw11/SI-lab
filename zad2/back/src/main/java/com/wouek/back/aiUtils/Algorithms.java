package com.wouek.back.aiUtils;


import com.wouek.back.entity.Result;
import com.wouek.back.resetrictions.ColumnConstraint;
import com.wouek.back.resetrictions.RowConstraint;
import com.wouek.back.resetrictions.AreaConstraint;
import com.wouek.back.entity.Sudoku;
import com.wouek.back.entity.SudokuVariable;

import java.util.ArrayList;

public class Algorithms {

    public Sudoku sudoku;
    ArrayList<SudokuVariable> track;
    public ArrayList<Sudoku> solutions;

    public Algorithms(Sudoku sudoku) {
        this.sudoku = sudoku;
        track = new ArrayList<>();
        solutions = new ArrayList<>();
    }


    public Result run() {
        long l = System.currentTimeMillis();
        int visited = 0;
        int backed = 0;
        sudoku.restrictAllDomains();
        SudokuVariable variable;
        RowConstraint rowConstraint = new RowConstraint();
        ColumnConstraint columnConstraint = new ColumnConstraint();
        AreaConstraint squareConstraint = new AreaConstraint();
        ArrayList<SudokuVariable> empties = sudoku.getEmpties();
        int index = 0;
        while (index < empties.size()) {
            variable = empties.get(index);
            boolean isOk;
            do {
                isOk = false;
                int nextValue;
                if (variable.domain.getPossibleValues().size() > 0) {
                    visited++;
                    nextValue = variable.domain.getPossibleValues().get(0);
                    variable.value = nextValue;

                    if (rowConstraint.isOK(variable, sudoku) && columnConstraint.isOK(variable, sudoku) && squareConstraint.isOK(variable, sudoku)) {
                        isOk = true;
                        index++;
                    } else {
                        variable.restrictDomain(variable.value);
                        variable.value = 0;
                    }
                } else {
                    backed++;
                    index--;
                    variable.value = 0;
                    SudokuVariable lastVariable = variable;
                    lastVariable.setNewDomain();
                    variable = empties.get(index);
                    int lastValue = variable.value;
                    variable.restrictDomain(lastValue);
                    variable.value = 0;
                    for (int j = index + 1; j < empties.size(); j++) {
                        empties.get(j).setNewDomain();
                        sudoku.restrictDomainForVariable(empties.get(j));
                    }

                    sudoku.emptyValues++;
                }
            } while (!isOk);
            sudoku.emptyValues--;
        }
        sudoku.getPuzzles();
        return new Result(sudoku.getPuzzles(), visited, backed, System.currentTimeMillis()-l);
    }


}
