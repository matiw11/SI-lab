package com.wouek.backendv2.variablePicker;

import com.wouek.backendv2.domain.CSPVariables;
import com.wouek.backendv2.domain.Cords;

import java.util.List;

public class RandomVariablePicker implements VariablePicker {
    @Override
    public Cords pickVariable(CSPVariables cspVariables) {
        List<Cords> freeCords = cspVariables.getFreeCords();
        int v = (int) (Math.random() * freeCords.size());
        return  new Cords(freeCords.get(v).getColumn(), freeCords.get(v).getRow());
    }
}
