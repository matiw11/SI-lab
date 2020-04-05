package com.wouek.back.entity;


public class SudokuVariable {
    public int row;
    public int column;
    public Domain domain;
    public int value = 0;

    public SudokuVariable(int value, int row, int column){
        domain = new Domain(value);
        this.value = value;
        this.row = row;
        this.column = column;
    }
    public SudokuVariable(int row, int column){
        domain = new Domain();
        this.row = row;
        this.column = column;
    }

    @Override
    public String toString() {
        if (value==0)
            return " . ";
        else
            return " " + value + " ";
    }

    public boolean checkIfCanSaveValueAndSave(int value){
        if (domain.hasOneValue()){
            this.value = value;
            return true;
        }
        return false;
    }

    public void restrictDomain(int value){
        domain.possibleValues.remove(Integer.valueOf(value));

    }

    public void setNewDomain() {
        domain = new Domain();
    }

    public SudokuVariable copy() {
        return new SudokuVariable(value, row, column);
    }
}
