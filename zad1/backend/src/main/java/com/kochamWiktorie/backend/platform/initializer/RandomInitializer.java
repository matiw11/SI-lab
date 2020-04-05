package com.kochamWiktorie.backend.platform.initializer;

import com.kochamWiktorie.backend.platform.Individual;
import com.kochamWiktorie.backend.platform.TSPCoordinates;

public class RandomInitializer implements Initializer {
    @Override
    public Individual initialize(TSPCoordinates tspCoordinates) {
        return Individual.createRandomIndividual(tspCoordinates.coords.size());
    }
}
