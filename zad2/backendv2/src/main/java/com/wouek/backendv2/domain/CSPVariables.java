package com.wouek.backendv2.domain;

import com.wouek.backendv2.restriction.Restriction;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.SerializationUtils;

import java.util.ArrayList;
import java.util.List;

public class CSPVariables {
    private CSPVariable[][] cspVariables = new CSPVariable[9][9];
    private List<Cords> freeCords = new ArrayList<>();
    private List<Restriction> restrictions = new ArrayList<>();

    public CSPVariables(List<Restriction> restrictions) {
        this();
        this.restrictions = restrictions;

    }

    public CSPVariable[][] getVariables() {
        return cspVariables;
    }

    public int[][] getVariableValues() {
        int[][] values = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                values[j][i] = this.cspVariables[j][i].getValue();
            }
        }
        return values;
    }

    public CSPVariables(CSPVariable[][] cspVariables, List<Cords> freeCords, List<Restriction> restrictions) {
        this.cspVariables = cspVariables;
        this.freeCords = freeCords;
        this.restrictions = restrictions;
    }

    public CSPVariables clone() {
        List<Cords> freeCords = new ArrayList<>(this.freeCords);
        CSPVariable[][] cspVariables = new CSPVariable[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cspVariables[j][i] = this.cspVariables[j][i].clone();

            }
        }
        return new CSPVariables(cspVariables, freeCords, restrictions);


    }

    public CSPVariable getVariable(int column, int row) {
        return cspVariables[column][row];
    }

    public boolean addVariable(int column, int row, int value) {
        if (!cspVariables[column][row].domainContains(value))
            return false;
        for (Restriction restriction : restrictions) {
            if(!restriction.restrict(cspVariables, column, row, value)){
                return false;
            }
        }
        cspVariables[column][row].setValue(value);

        boolean delted = freeCords.remove(new Cords(column, row, value));
        // System.out.println(delted);
        return true;
    }

    public boolean removeVariable(int column, int row) {
        if (cspVariables[column][row].getValue() < 1) {
            return false;
        }
        for (Restriction restriction : restrictions) {
            restriction.unRestrict(cspVariables, column, row);
        }
        cspVariables[column][row].setValue(0);
        freeCords.add(new Cords(column, row));

        return true;
    }

    public CSPVariables() {
        initializeVariables();
    }

    private void initializeVariables() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cspVariables[j][i] = new CSPVariable();
                freeCords.add(new Cords(j, i));
            }
        }
    }

    public void print() {
        String res = "";
        System.out.println("_________");
        for (int i = 0; i < 9; i++) {
            System.out.print("|");
            for (int j = 0; j < 9; j++) {
                System.out.print(cspVariables[j][i].getValue() > 0 ? cspVariables[j][i].getValue() : " ");
                res+= cspVariables[j][i].getValue();
            }
            System.out.println("|");
        }
        System.out.println("_________");
        System.out.println(res);
    }

    public List<Cords> getFreeCords() {
        return freeCords;
    }

    public void addRestriction(Restriction restriction) {
        restrictions.add(restriction);
    }


}
