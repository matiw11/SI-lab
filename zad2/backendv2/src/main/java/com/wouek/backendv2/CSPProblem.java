package com.wouek.backendv2;

import com.wouek.backendv2.ValuePicker.NextValuePicker;
import com.wouek.backendv2.ValuePicker.ValuePicker;
import com.wouek.backendv2.domain.CSPVariable;
import com.wouek.backendv2.domain.CSPVariables;
import com.wouek.backendv2.domain.Cords;
import com.wouek.backendv2.domain.Result;
import com.wouek.backendv2.restriction.ColumnRestriction;
import com.wouek.backendv2.restriction.RowRestriction;
import com.wouek.backendv2.restriction.SquareRestriction;
import com.wouek.backendv2.utlis.Parser;
import com.wouek.backendv2.variablePicker.NextVariablePicker;
import com.wouek.backendv2.variablePicker.VariablePicker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CSPProblem {
    public CSPVariables cspVariables = new CSPVariables();
    public ValuePicker valuePicker = new NextValuePicker();
    public VariablePicker variablePicker = new NextVariablePicker();
    List<CSPVariables> solutions = new ArrayList<>();
    private int firstVisited;
    private int firstBacked;
    private int allVisited;
    private int allBacked;
    private double timeToFirst;
    private double startTime;
    private double finishTime;


    public CSPProblem(int sudokuIndex) {
        RowRestriction rowRestriction = new RowRestriction();
        ColumnRestriction columnRestriction = new ColumnRestriction();
        SquareRestriction squareRestriction = new SquareRestriction();
        cspVariables.addRestriction(columnRestriction);
        cspVariables.addRestriction(rowRestriction);
        cspVariables.addRestriction(squareRestriction);
        List<Cords> parse = new Parser().parse(sudokuIndex);
        for (Cords cords : parse) {
            cspVariables.addVariable(cords.getColumn(), cords.getRow(), cords.getValue());
        }
        cspVariables.print();
    }
    public CSPProblem(int sudokuIndex, VariablePicker variablePicker, ValuePicker valuePicker){
        this(sudokuIndex);
        this.variablePicker = variablePicker;
        this.valuePicker = valuePicker;
    }

    public Result solve() {
        startTime = System.currentTimeMillis();
        recurrentSolve(cspVariables);
        finishTime = System.currentTimeMillis() - startTime;
        return new Result(solutions.get(0).getVariableValues(), timeToFirst, firstVisited, firstBacked, finishTime, allVisited, allBacked, solutions.size());

    }


    private void recurrentSolve(CSPVariables variables) {
        if (variables.getFreeCords().isEmpty()) {
            solutions.add(variables);
            if (timeToFirst == 0) {
                firstBacked = allBacked;
                firstVisited = allVisited;
                timeToFirst = System.currentTimeMillis() - startTime;
            }
            return;
        }
        Cords cords = variablePicker.pickVariable(variables);
        CSPVariable variable = variables.getVariable(cords.getColumn(), cords.getRow());
        Set<Integer> domain = new HashSet<>(variable.getDomain());
        while (domain.size() != 0) {
            CSPVariables clonedVariables = variables.clone();
            int pickValue = valuePicker.pickValue(domain);
            domain.remove(pickValue);
            allVisited++;
            if (clonedVariables.addVariable(cords.getColumn(), cords.getRow(), pickValue)) {

                recurrentSolve(clonedVariables);
            }
        }
        allBacked++;

    }

}


