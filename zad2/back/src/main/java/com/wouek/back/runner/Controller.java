package com.wouek.back.runner;

import com.wouek.back.aiUtils.Algorithms;
import com.wouek.back.entity.Result;
import com.wouek.back.entity.Sudoku;
import com.wouek.back.parser.Parser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@CrossOrigin
public class Controller {

    @RequestMapping("/start/{id}")
    public Result start(@PathVariable String id){

        int idInt = Integer.parseInt(id);
        Parser parser = new Parser();
        Sudoku sudoku = null;
        try {
            sudoku = parser.loadSudoku(idInt, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sudoku.restrictAllDomains();


        Algorithms alg = new Algorithms(sudoku);
        Result result = alg.run();
        return result;
    }
    @RequestMapping("/get/{id}")
    public int[][] get(@PathVariable String id){
        int idInt = Integer.parseInt(id);
        Parser parser = new Parser();
        Sudoku sudoku = null;
        try {
            sudoku = parser.loadSudoku(idInt, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sudoku.restrictAllDomains();
        return sudoku.getPuzzles();
    }
}
