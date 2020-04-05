package com.wouek.backendv2.restriction;

import com.wouek.backendv2.domain.CSPVariable;

public class ColumnRestriction implements Restriction {
    @Override
    public boolean restrict(CSPVariable[][] cspVariables, int column, int row, int value) {
        for (int i = 0; i < 9; i++) {
            CSPVariable cspVariable = cspVariables[column][i];
            cspVariable.removeFromDomain(value);
            if(cspVariable.getDomain().isEmpty() && cspVariable.getValue()<1 && i != row){
                return false;
            }

            /*if(!b){
                System.out.println(this.getClass() + " ERR");
            }*/
        }
        return true;
    }

    @Override
    public void unRestrict(CSPVariable[][] cspVariables, int column, int row) {
        for (int i = 0; i < 9; i++) {
            boolean b = cspVariables[column][i].addToDomain(cspVariables[column][row].getValue());
            /*if(!b){
               // System.out.println(this.getClass() +" ERR");
            }*/
        }
    }
}
