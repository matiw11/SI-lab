package com.wouek.backendv2.restriction;

import com.wouek.backendv2.domain.CSPVariable;

public class RowRestriction implements Restriction {

    @Override
    public boolean restrict(CSPVariable[][] cspVariables, int column, int row, int value) {
        for (int i = 0; i < 9; i++) {
            CSPVariable cspVariable = cspVariables[i][row];
            cspVariable.removeFromDomain(value);
            if(cspVariable.getDomain().isEmpty() && cspVariable.getValue()<1 && i != column){
                return false;
            }
        }
        return true;
    }

    @Override
    public void unRestrict(CSPVariable[][] cspVariables, int column, int row) {
        for (int i = 0; i < 9; i++) {
            boolean b = cspVariables[i][row].addToDomain(cspVariables[column][row].getValue());
            if(!b){
             //   System.out.println("Err");
            }
        }
    }
}
