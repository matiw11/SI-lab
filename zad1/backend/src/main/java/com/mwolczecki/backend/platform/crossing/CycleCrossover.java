package com.mwolczecki.backend.platform.crossing;

import com.mwolczecki.backend.platform.Individual;

import java.util.ArrayList;
import java.util.List;

public class CycleCrossover implements CrossingAlgorithm {
    @Override
    public Individual cross(Individual firstIndividual, Individual secondIndividual) {
        Individual clone1 = null;
        Individual clone2 = null;
        try {
            clone1 = (Individual) firstIndividual.clone();
            clone2 = (Individual) secondIndividual.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }


        final List<Integer> cycleIndices = new ArrayList<>();
        int tour1index = (int) (Math.random() * firstIndividual.citiesIndexes.size());

        // add that index to the cycle indices

        cycleIndices.add(tour1index);

        // get the city in tour2 at that index
        int tour2city = secondIndividual.citiesIndexes.get(tour1index);

        // get the index of that city in tour1
        tour1index = firstIndividual.citiesIndexes.indexOf(tour2city);

        // if tour1index = initial index, stop
        while (tour1index != cycleIndices.get(0)) {

            // add that index to the cycle indices
            cycleIndices.add(tour1index);

            // get the city in tour2 at that index
            tour2city = secondIndividual.citiesIndexes.get(tour1index);

            // get the index of that city in tour1
            tour1index = firstIndividual.citiesIndexes.indexOf(tour2city);
        }

        // swap the cities at each of the indices of the determined cycle
        for (final int index : cycleIndices) {
            Integer integer = clone1.citiesIndexes.get(index);
            clone1.citiesIndexes.set(index, secondIndividual.citiesIndexes.get(index));
            clone2.citiesIndexes.set(index, integer);
        }
        return clone1;
    }
}
