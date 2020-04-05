package com.wouek.backend.core;

import java.util.HashSet;
import java.util.Set;

public class CSPVariable {

    public int value;
    public Set<Integer> domain;

    public static CSPVariable clone(CSPVariable variable){
        CSPVariable cspVariable = new CSPVariable();
        cspVariable.value = variable.value;
        Set<Integer> copySet = new HashSet<>();
        variable.domain.forEach(val -> copySet.add(new Integer(val)));
        cspVariable.domain = copySet;
        return cspVariable;
    }
}
