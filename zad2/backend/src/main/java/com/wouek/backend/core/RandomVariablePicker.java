package com.wouek.backend.core;

import java.util.Random;
import java.util.Set;

public class RandomVariablePicker implements VariablePicker {
    @Override
    public IntPair pickVariable(CSPVariables cspVariables, Set<IntPair> avaliable) {
        int size = avaliable.size();
        int item = (int) (Math.random()*size); // In real life, the Random object should be rather more shared than this
        int i = 0;
        for(IntPair obj : avaliable)
        {
            if (i == item)
                return obj;
            i++;
        }
        return null;
    }
}
