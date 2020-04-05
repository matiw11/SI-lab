package com.wouek.backend.core;

import java.util.List;
import java.util.Set;

public class RandomValuePIcker implements ValuePicker {
    @Override
    public int pickValue(List<Integer> avaliableValues) {
        return avaliableValues.get((int) (Math.random()*avaliableValues.size()));
    }
}
