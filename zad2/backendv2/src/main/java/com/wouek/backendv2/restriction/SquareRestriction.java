package com.wouek.backendv2.restriction;

import com.wouek.backendv2.domain.CSPVariable;

public class SquareRestriction implements Restriction {
    @Override
    public boolean restrict(CSPVariable[][] cspVariables, int column, int row, int value) {
        int columnStart = getStartValue(column);
        int rowStart = getStartValue(row);

        for (int i = columnStart; i < columnStart + 3; i++) {
            for (int j = rowStart; j < rowStart + 3; j++) {
                CSPVariable cspVariable = cspVariables[i][j];
                cspVariable.removeFromDomain(value);
                if(cspVariable.getDomain().isEmpty() && cspVariable.getValue() < 1 && i != column && j!=row){
                    return false;
                }
            }
        }
        return true;
    }


    private int getStartValue(int number) {
        if (number <= 2)
            return 0;
        else if (number <= 5)
            return 3;
        else
            return 6;
    }
    @Override
    public void unRestrict(CSPVariable[][] cspVariables, int column, int row) {

    }
}
