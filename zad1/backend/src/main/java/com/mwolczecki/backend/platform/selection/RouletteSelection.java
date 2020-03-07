package com.mwolczecki.backend.platform.selection;

import com.mwolczecki.backend.platform.Individual;
import com.mwolczecki.backend.platform.TSPCoordinates;

import java.util.List;

public class RouletteSelection implements SelectionAlgorithm {
    @Override
    public Individual select(List<Individual> individuals, TSPCoordinates coordinates) {
        double sum =0;
        for(Individual individual: individuals){
            //left for scaling
            sum+= 1/Math.pow(individual.calculateRoute(coordinates),1);
        }
        double random = Math.random()*sum;
        double sum2 =0;
        for(Individual individual: individuals){
            sum2+= 1/Math.pow(individual.calculateRoute(coordinates),1);
            if(sum2>= random){
                return individual;
            }
        }
        return null;
    }
}
