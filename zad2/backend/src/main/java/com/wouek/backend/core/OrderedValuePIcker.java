package com.wouek.backend.core;

import java.util.List;

public class OrderedValuePIcker implements ValuePicker {
    @Override
    public int pickValue(List<Integer> avaliableValues) {
        return  avaliableValues.get(0);
    }
}
