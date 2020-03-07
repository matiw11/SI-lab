package com.mwolczecki.backend.platform.initializer;

import com.mwolczecki.backend.platform.Individual;
import com.mwolczecki.backend.platform.TSPCoordinates;

public class MixedInitializer implements Initializer {
    @Override
    public Individual initialize(TSPCoordinates tspCoordinates) {
        if(Math.random()>0.5) {
            return Individual.createRandomIndividual(tspCoordinates.coords.size());
        }else{
            return Individual.createGreedyIndividual(tspCoordinates, (int) (Math.random()*tspCoordinates.coords.size()));
        }
    }
}
