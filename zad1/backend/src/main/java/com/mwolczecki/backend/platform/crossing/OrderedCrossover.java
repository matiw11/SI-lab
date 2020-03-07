package com.mwolczecki.backend.platform.crossing;

import com.mwolczecki.backend.platform.Individual;
import com.mwolczecki.backend.platform.crossing.CrossingAlgorithm;

import java.util.List;

public class OrderedCrossover implements CrossingAlgorithm {
    @Override
    public Individual cross(Individual firstIndividual, Individual secondIndividual) {
        int firstIndex = (int) (Math.random() * firstIndividual.citiesIndexes.size());
        int secondIndex = (int) (Math.random() * firstIndividual.citiesIndexes.size());
        if (firstIndex > secondIndex) {
            int temp = firstIndex;
            firstIndex = secondIndex;
            secondIndex = temp;
        }

        final Individual clone;
        try {
            clone = (Individual) secondIndividual.clone();
            List<Integer> subList = firstIndividual.citiesIndexes.subList(firstIndex, secondIndex);
            for(Integer index: subList){
                clone.citiesIndexes.remove((Object)index);
            }

            clone.citiesIndexes.addAll(firstIndex, subList);
            return clone;

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return null;
    }
}
