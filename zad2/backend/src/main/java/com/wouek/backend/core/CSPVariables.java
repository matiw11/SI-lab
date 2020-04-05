package com.wouek.backend.core;

import java.util.*;

public class CSPVariables {
    public Map<IntPair, CSPVariable> cspVariables = new HashMap<>();

    public CSPVariables() {
        cspVariables = generateEmpySudoku();
    }

    public static CSPVariables clone(CSPVariables base) {
        CSPVariables cspVariables = new CSPVariables();
        Map<IntPair, CSPVariable> copyMap = new HashMap<>();
        base.cspVariables.entrySet().forEach(entry -> copyMap.put(entry.getKey(), CSPVariable.clone(entry.getValue())));
        cspVariables.cspVariables = copyMap;
        return cspVariables;
    }

    private Map<IntPair, CSPVariable> generateEmpySudoku() {
        Map<IntPair, CSPVariable> variables = new HashMap<>();
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                IntPair intPair = new IntPair(j, i);
                CSPVariable cspVariable = new CSPVariable();
                HashSet<Integer> integers = new HashSet<>();
                integers.add(1);
                integers.add(2);
                integers.add(3);
                integers.add(4);
                integers.add(5);
                integers.add(6);
                integers.add(7);
                integers.add(8);
                integers.add(9);
                cspVariable.domain = integers;
                variables.put(intPair, cspVariable);
            }
        }
        return variables;
    }

    public boolean put(IntPair key, Integer value) {
        if (cspVariables.get(key).value == 0 && cspVariables.get(key).domain.contains(value)) {
            cspVariables.get(key).value = value;
            HashSet<Integer> integers = new HashSet<>();
            integers.add(value);
            cspVariables.get(key).domain = integers;
            updateAllContraints(key, value);
            return true;
        }
        return false;
    }

    private void updateAllContraints(IntPair key, Integer value) {
      updateSection(key, value);
        updateRows(key, value);
       updateColumns(key, value);
    }

    private void updateSection(IntPair key, Integer value) {
        int xStart = getStartValue(key.x);
        int yStart = getStartValue(key.y);

        for (int i = xStart; i < xStart + 3; i++) {
            for (int j = yStart; j < yStart + 3; j++) {
                cspVariables.get(new IntPair(i, j)).domain.remove(value);
            }
        }
    }

    private int getStartValue(int number) {
        if (number <= 3)
            return 1;
        else if (number <= 6)
            return 4;
        else
            return 7;
    }

    private void updateRows(IntPair key, Integer value) {
        cspVariables.entrySet()
                .stream()
                .filter(entry -> entry.getKey().y == key.y)
                .forEach(filteredEntry -> cspVariables.get(filteredEntry.getKey()).domain.remove(value));
    }

    private void updateColumns(IntPair key, Integer value) {
        cspVariables.entrySet()
                .stream()
                .filter(entry -> entry.getKey().x == key.x)
                .forEach(filteredEntry -> cspVariables.get(filteredEntry.getKey()).domain.remove(value));
    }


    private CSPVariable generateCSPVariable(Map.Entry<IntPair, Integer> entry) {
        CSPVariable cspVariable = new CSPVariable();
        cspVariable.value = entry.getValue();

        cspVariable.domain = Set.of(entry.getValue());
        return cspVariable;
    }

}
