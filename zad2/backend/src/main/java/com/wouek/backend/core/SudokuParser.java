package com.wouek.backend.core;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SudokuParser {

    public static void main(String[] args)
    {
        long l = System.currentTimeMillis();

        Map<IntPair, Integer> parse = new SudokuParser().parse(2);
        CSP csp = new CSP();
        parse = csp.run(parse);
        System.out.println(parse);
        for(int i =1; i<=9; i++){
            for(int j =1; j<=9; j++){
                if(parse.containsKey(new IntPair(j,i))){
                    System.out.print(parse.get(new IntPair(j,i)));
                }else{
                    System.out.print(".");
                }
            }
            System.out.println();
        }
        System.out.println(System.currentTimeMillis() - l);
    }

    public Map<IntPair, Integer> parse(int recordNumber){
        Map<IntPair, Integer> map = new HashMap<>();
        InputStream resourceAsStream = SudokuParser.class.getClassLoader().getResourceAsStream("Sudoku.csv");
        String vals="";
        try {
            CSVParser parser = CSVParser.parse(resourceAsStream, Charset.defaultCharset(), CSVFormat.RFC4180);
            for(CSVRecord record: parser){
                if(record.getRecordNumber() == recordNumber){
                    vals = record.get(0).split(";")[2];

                }
            }
            for(int i =1;i<=9;i++){
                for(int j =1;j<=9; j++){
                    int currind = 9*(i-1) + j -1;
                    char c = vals.charAt(currind);
                    String v = ""+ c;
                    if(!v.equals(".")){
                        map.put(new IntPair(j,i), Integer.valueOf(v));
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
