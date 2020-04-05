package com.wouek.backend.core;

import java.util.*;

public class CSP {
    VariablePicker variablePicker = new RandomVariablePicker();
    ValuePicker valuePicker = new RandomValuePIcker();
    Map<IntPair, Integer> solvedSudoku;
    CSPVariables solution;
    int a = 0;
    public Map<IntPair, Integer> run(Map<IntPair, Integer> sudoku) {
        CSPVariables cspVariables = generateCSPVariables(sudoku);
        solvedSudoku = findSolution(cspVariables);
        return solvedSudoku;
    }

    private Map<IntPair, Integer> findSolution(CSPVariables cspVariables) {
        Set<IntPair> avaliableVariables = new HashSet<>();
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                avaliableVariables.add(new IntPair(j, i));
            }
        }
        cspVariables.cspVariables.entrySet().forEach(entry -> {
            if (entry.getValue().value != 0) {
                avaliableVariables.remove(entry.getKey());
            }
        });
        CSPVariables clonedVariables = CSPVariables.clone(cspVariables);
        Set<IntPair> clonedAvaliableVariables = new HashSet<>(avaliableVariables);
       recurrentFindSolution(cspVariables, avaliableVariables);
/*
        while (solution ==null){

        }*/



        Map<IntPair, Integer> map = new HashMap<>();
        solution.cspVariables.entrySet().forEach(entry -> {
            map.put(entry.getKey(), entry.getValue().value);
        });
        return map;
    }

    private void recurrentFindSolution(CSPVariables cspVariables, Set<IntPair> avaliableVariables) {
        if(avaliableVariables.size() == 0){
            this.solution = cspVariables;
            return;
        }
        for(Map.Entry<IntPair, CSPVariable> entry: cspVariables.cspVariables.entrySet()){
            if(entry.getValue().value == 0 && entry.getValue().domain.size() == 0){
                return;
            }
        }

        for(IntPair intPair: avaliableVariables){
            List<Integer> avaliableValues = new ArrayList<>(cspVariables
                    .cspVariables
                    .get(intPair)
                    .domain);
            while (!avaliableValues.isEmpty()) {
                int value = valuePicker.pickValue(avaliableValues);
                boolean remove = avaliableValues.remove(new Integer(value));
                CSPVariables clone = CSPVariables.clone(cspVariables);
                boolean put = clone.put(intPair, value);
                Set<IntPair> copy = new HashSet<>(avaliableVariables);
                if (put) {
                    copy.remove(intPair);
                    recurrentFindSolution(clone, copy);
                }
            }
        }
        //IntPair intPair = variablePicker.pickVariable(cspVariables, avaliableVariables);


    }

    private CSPVariables generateCSPVariables(Map<IntPair, Integer> sudoku) {
        //chceckIFValid
        //generatevalueswithdomains
        CSPVariables cspVariables = new CSPVariables();//new HashMap<IntPair, CSPVariable>();
        sudoku.forEach(cspVariables::put);
        return cspVariables;
    }

    private void updateAllDomains(Map<IntPair, CSPVariable> cspVariables) {
    }


}

