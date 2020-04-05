package com.kochamWiktorie.backend.platform.mutation;

import com.kochamWiktorie.backend.platform.Individual;

import java.util.Collections;
import java.util.List;

public class InversionMutation implements MutationAlogirthm {
    @Override
    public Individual mutate(Individual individual) {
        int firstIndex = (int) (Math.random() * individual.citiesIndexes.size());
        int secondIndex = (int) (Math.random() * individual.citiesIndexes.size());
        if (firstIndex > secondIndex) {
            int temp = firstIndex;
            firstIndex = secondIndex;
            secondIndex = temp;
        }
        try {
            Individual clone = (Individual)individual.clone();
            List<Integer> subList = individual.citiesIndexes.subList(firstIndex, secondIndex);
            clone.citiesIndexes.removeAll(subList);
            Collections.reverse(subList);
            clone.citiesIndexes.addAll(firstIndex,subList);
            return clone;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return null;
    }
}
