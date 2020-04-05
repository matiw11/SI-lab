package com.wouek.back;

import com.wouek.back.aiUtils.Algorithms;
import com.wouek.back.parser.Parser;
import com.wouek.back.entity.Sudoku;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class BackApplication {

    public static void main(String[] args) {

        SpringApplication.run(BackApplication.class, args);

    }

}
