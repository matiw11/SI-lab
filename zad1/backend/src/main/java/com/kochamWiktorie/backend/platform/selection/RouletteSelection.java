package com.kochamWiktorie.backend.platform.selection;

import com.kochamWiktorie.backend.platform.Individual;
import com.kochamWiktorie.backend.platform.TSPCoordinates;

import java.util.List;

public class RouletteSelection implements SelectionAlgorithm {
    @Override
    public Individual select(List<Individual> individuals, TSPCoordinates coordinates) {
        double sum =0;
        for(Individual individual: individuals){
            //left for scaling
            sum+= 1/Math.pow(individual.calculateRoute(coordinates),10);
        }
        double random = Math.random()*sum;
        double sum2 =0;
        for(Individual individual: individuals){
            sum2+= 1/Math.pow(individual.calculateRoute(coordinates),10);
            if(sum2>= random){
                return individual;
            }
        }
        return null;
    }
}
