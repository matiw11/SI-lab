package com.wouek.back.entity;


import com.wouek.back.resetrictions.ColumnConstraint;
import com.wouek.back.resetrictions.Constraint;
import com.wouek.back.resetrictions.RowConstraint;
import com.wouek.back.resetrictions.AreaConstraint;

import java.util.ArrayList;

public class Sudoku {
    public SudokuVariable[][] puzzle = new SudokuVariable[9][9];
    public int emptyValues = 81;

    public void loadVariable(char value, int row, int column){
        if(value=='.'){
            puzzle[row][column] = new SudokuVariable(row, column);
        }
        else {
            puzzle[row][column] = new SudokuVariable(Integer.parseInt(String.valueOf(value)), row, column);
            emptyValues--;
        }
    }

    public int[][] getPuzzles(){
        int [][] puz = new int[9][9];
        for(int i =0;i<9;i++){
            for(int j=0; j<9; j++){
                puz[i][j]  = puzzle[i][j].value;
            }
        }
        return puz;
    }


    private void restrictDomain(Constraint constraint, int row, int column){
        SudokuVariable variable = puzzle[row][column];
        if (variable.value!=0){
            return;
        }
        SudokuVariable[] scope = constraint.getScope(variable, this);
        ArrayList<Integer> alreadyInPuzzle = new ArrayList<>();
        for (int i =0; i<9; i++){
            if(scope[i].value!=0){
                alreadyInPuzzle.add(scope[i].value);
            }
        }
        for (int i =0; i<9; i++){
            if(scope[i].value==0){
                for (Integer value :
                        alreadyInPuzzle) {
                    scope[i].restrictDomain(value);
                }
            }
        }
    }


    public void restrictAllDomains(){
        for(int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                if(puzzle[i][j].value==0){
                    restrictDomainForVariable(puzzle[i][j]);
                }

            }
        }
    }

    public void restrictDomainForVariable(SudokuVariable variable){
        RowConstraint rowConstraint = new RowConstraint();
        ColumnConstraint columnConstraint = new ColumnConstraint();
        AreaConstraint squareConstraint = new AreaConstraint();
        restrictDomain(rowConstraint, variable.row,variable.column);
        restrictDomain(columnConstraint, variable.row,variable.column);
        restrictDomain(squareConstraint, variable.row,variable.column);
    }

    public SudokuVariable findSmallestDomain(){
        int smallestDomain = 9;
        SudokuVariable variable = null;
        ArrayList<SudokuVariable> vars = new ArrayList<>();
        for(int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                if(puzzle[i][j].domain.getPossibleValues().size()==smallestDomain && puzzle[i][j].value==0)
                {
                    smallestDomain = puzzle[i][j].domain.getPossibleValues().size();
                    variable = puzzle[i][j];
                    vars.add(puzzle[i][j]);
                }
                if(puzzle[i][j].domain.getPossibleValues().size()<smallestDomain && puzzle[i][j].value==0){
                    vars.clear();
                    vars.add(puzzle[i][j]);
                }

            }
        }

        return vars.get((int)(Math.random()*vars.size()));
    }

    public SudokuVariable getRandomVariable(){
        if (emptyValues==0)
            return null;
        int randx, randy;
        do {
            randx = (int)( Math.random()*puzzle.length);
            randy = (int)( Math.random()*puzzle.length);
        }while (puzzle[randx][randy].value!=0);
        return puzzle[randx][randy];
    }

    public void setNewDomains(){
        for (int i =0; i<9; i++){
            for (int j=0; j<9; j++){
                if (puzzle[i][j].value==0){
                    puzzle[i][j].setNewDomain();
                }
            }
        }
        restrictAllDomains();
    }

    public ArrayList<SudokuVariable> getEmpties(){
        ArrayList<SudokuVariable> empties = new ArrayList<>();
        for (int i =0; i<9; i++){
            for (int j=0; j<9; j++){
                if (puzzle[i][j].value==0){
                    empties.add(puzzle[i][j]);
                }
            }
        }
        return empties;
    }

    public Sudoku copy() {
        Sudoku tmp = new Sudoku();
        for (int i =0; i<9; i++){
            for (int j=0; j<9; j++){
                tmp.puzzle[i][j] = puzzle[i][j].copy();
            }
        }
        return tmp;
    }
}
