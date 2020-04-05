package com.wouek.back.parser;


import com.wouek.back.entity.Sudoku;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parser {

    public Parser(){

    }

    public Sudoku loadSudoku(int sudokuId, boolean isSolution) throws IOException {
        String[] data = getLine(sudokuId);
        assert data != null;
        String puzzle;
        if(isSolution){
            puzzle = data[3];
        }
        else {
            puzzle = data[2];
        }

        Sudoku sudoku = new Sudoku();
        int counter = 0;
        for(int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                sudoku.loadVariable(puzzle.toCharArray()[counter], i, j);
                counter++;
            }
        }
        return sudoku;
    }

    private String[] getLine(int sudokuId) throws IOException {

        BufferedReader csvReader = new BufferedReader(new FileReader("src/main/resources/Sudoku.csv"));
        String row;
        String[] data;
        csvReader.readLine();
        while ((row = csvReader.readLine()) != null) {
            data = row.split(";");
            if(Integer.parseInt(data[0])==sudokuId) {
                csvReader.close();
                return data;
            }
        }
        csvReader.close();
        return null;
    }


}
