package com.wouek.backendv2.restriction;

import com.wouek.backendv2.domain.CSPVariable;

public interface Restriction {
    public boolean restrict(CSPVariable [][] cspVariables, int column, int row, int value);
    public void unRestrict(CSPVariable [][] cspVariables, int column, int row);
}
