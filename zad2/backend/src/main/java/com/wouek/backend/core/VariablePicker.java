package com.wouek.backend.core;

import java.util.Set;

public interface VariablePicker {

    public IntPair pickVariable(CSPVariables cspVariables, Set<IntPair> avaliable);
}
