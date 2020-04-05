package com.kochamWiktorie.backend.platform.crossing;

import com.kochamWiktorie.backend.platform.Individual;

import java.util.List;

public class PartiallyMatchedCrossover implements CrossingAlgorithm {
    @Override
    public Individual cross(Individual firstIndividual, Individual secondIndividual) {
        int firstIndex = (int) (Math.random() * firstIndividual.citiesIndexes.size());
        int secondIndex = (int) (Math.random() * firstIndividual.citiesIndexes.size());
        if (firstIndex > secondIndex) {
            int temp = firstIndex;
            firstIndex = secondIndex;
            secondIndex = temp;
        }

        try {
            Individual clone = (Individual) secondIndividual.clone();
            List<Integer> subList = firstIndividual.citiesIndexes.subList(firstIndex, secondIndex);
            List<Integer> cloneSubList = clone.citiesIndexes.subList(firstIndex, secondIndex);
            for (int i = 0; i < subList.size(); i++) {
                int j = clone.citiesIndexes.indexOf(subList.get(i));
                clone.citiesIndexes.set(j, cloneSubList.get(i));
                clone.citiesIndexes.set(firstIndex + i, subList.get(i));
            }
            return clone;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;


    }
}
