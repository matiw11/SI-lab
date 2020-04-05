package com.wouek.backendv2.ValuePicker;

import java.util.Set;

public class NextValuePicker implements ValuePicker {
    @Override
    public int pickValue(Set<Integer> domain) {
        for(int i = 1; i<=9; i++){
            if (domain.contains(i)){
                return i;
            }
        }
        return -1;
    }
}
