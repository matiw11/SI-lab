package com.wouek.backendv2.variablePicker;

import com.wouek.backendv2.domain.CSPVariables;
import com.wouek.backendv2.domain.Cords;

import java.util.List;

public class NextVariablePicker implements VariablePicker {
    @Override
    public Cords pickVariable(CSPVariables cspVariables) {
        List<Cords> freeCords = cspVariables.getFreeCords();
        return  new Cords(freeCords.get(0).getColumn(), freeCords.get(0).getRow());
    }
}
