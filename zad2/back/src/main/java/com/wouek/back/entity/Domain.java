package com.wouek.back.entity;

import java.util.ArrayList;

public class Domain {
    ArrayList<Integer> possibleValues;

    Domain(){
        possibleValues = new ArrayList<>();
        for (int i = 1; i<=9; i++){
            possibleValues.add(i);
        }
    }

    Domain(int value){
        possibleValues = new ArrayList<>();
        possibleValues.add(value);
    }

    public ArrayList<Integer> getPossibleValues(){
        return possibleValues;
    }

    public boolean hasOneValue(){
        return possibleValues.size() <= 1;
    }

}
