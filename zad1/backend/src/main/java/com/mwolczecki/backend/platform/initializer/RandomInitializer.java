package com.mwolczecki.backend.platform.initializer;

import com.mwolczecki.backend.platform.Individual;
import com.mwolczecki.backend.platform.TSPCoordinates;

public class RandomInitializer implements Initializer {
    @Override
    public Individual initialize(TSPCoordinates tspCoordinates) {
        return Individual.createRandomIndividual(tspCoordinates.coords.size());
    }
}
