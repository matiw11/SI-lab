package com.wouek.backendv2.ValuePicker;

import java.util.Random;
import java.util.Set;

public class RandomValuePicker  implements ValuePicker{
    @Override
    public int pickValue(Set<Integer> domain) {
        int size = domain.size();
        int item = new Random().nextInt(size); // In real life, the Random object should be rather more shared than this
        int i = 0;
        for(Integer obj : domain)
        {
            if (i == item)
                return obj;
            i++;
        }
        return 0;
    }
}
