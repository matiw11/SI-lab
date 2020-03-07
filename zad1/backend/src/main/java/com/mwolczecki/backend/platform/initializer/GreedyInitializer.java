package com.mwolczecki.backend.platform.initializer;

import com.mwolczecki.backend.platform.Individual;
import com.mwolczecki.backend.platform.TSPCoordinates;

public class GreedyInitializer implements Initializer{

    @Override
    public Individual initialize(TSPCoordinates tspCoordinates) {
        return Individual.createGreedyIndividual(tspCoordinates, (int) (Math.random()*tspCoordinates.coords.size()));
    }
}
