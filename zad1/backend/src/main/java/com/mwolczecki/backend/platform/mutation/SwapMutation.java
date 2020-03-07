package com.mwolczecki.backend.platform.mutation;

import com.mwolczecki.backend.platform.Individual;

public class SwapMutation implements MutationAlogirthm {
    @Override
    public Individual mutate(Individual individual) {
        int firstIndex = (int) (Math.random() * individual.citiesIndexes.size());
        int secondIndex = (int) (Math.random() * individual.citiesIndexes.size());
        Individual clone = null;
        try {
            clone = (Individual) individual.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        Integer integer = clone.citiesIndexes.get(firstIndex);
        clone.citiesIndexes.set(firstIndex, clone.citiesIndexes.get(secondIndex));
        clone.citiesIndexes.set(secondIndex, integer );
        return clone;
    }
}
