package com.wouek.backendv2.variablePicker;

import com.wouek.backendv2.domain.CSPVariables;
import com.wouek.backendv2.domain.Cords;

public interface VariablePicker {

    public Cords pickVariable(CSPVariables cspVariables);

}
