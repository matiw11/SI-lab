package com.kochamWiktorie.backend.platform.initializer;

import com.kochamWiktorie.backend.platform.TSPCoordinates;
import com.kochamWiktorie.backend.platform.Individual;

public class GreedyInitializer implements Initializer{

    @Override
    public Individual initialize(TSPCoordinates tspCoordinates) {
        return Individual.createGreedyIndividual(tspCoordinates, (int) (Math.random()*tspCoordinates.coords.size()));
    }
    public Individual initialize(TSPCoordinates tspCoordinates, int index) {
        return Individual.createGreedyIndividual(tspCoordinates, index);
    }
}
