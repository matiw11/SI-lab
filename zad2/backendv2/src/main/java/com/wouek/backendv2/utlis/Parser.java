package com.wouek.backendv2.utlis;

import com.wouek.backendv2.domain.Cords;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {

    public List<Cords> parse(int recordNumber){
        recordNumber++;
        InputStream resourceAsStream = Parser.class.getClassLoader().getResourceAsStream("Sudoku.csv");
        ArrayList<Cords> cords = new ArrayList<>();
        String vals="";
        try {
            CSVParser parser = CSVParser.parse(resourceAsStream, Charset.defaultCharset(), CSVFormat.RFC4180);
            for(CSVRecord record: parser){
                if(record.getRecordNumber() == recordNumber){
                    vals = record.get(0).split(";")[2];

                }
            }
            int currInd = 0;
            for(int i =0;i<9;i++){
                for(int j =0;j<9; j++){
                    char c = vals.charAt(currInd);
                    currInd++;
                    String v = ""+ c;
                    if(!v.equals(".")){
                        cords.add(new Cords(j,i, Integer.valueOf(v)));
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cords;
    }
}
