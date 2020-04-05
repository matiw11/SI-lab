package com.wouek.backendv2.domain;

public class Cords {
    private int column;
    private int row;
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Cords(int column, int row, int value) {
        this.column = column;
        this.row = row;
        this.value = value;
    }

    public Cords(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public int hashCode() {
        return column*100 + row*10;
    }

    @Override
    public boolean equals(Object obj) {
        Cords c = (Cords) obj;
        return (c.column == this.column && c.row == this.row);
    }
}
