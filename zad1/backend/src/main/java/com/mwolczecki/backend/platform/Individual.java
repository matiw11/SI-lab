package com.mwolczecki.backend.platform;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Individual implements Cloneable {
    public List<Integer> citiesIndexes = new ArrayList<>();
    private double route = -1;
    public static Individual createRandomIndividual(int length) {
        Individual individual = new Individual();
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            temp.add(i);
        }
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * temp.size());
            individual.citiesIndexes.add(temp.get(index));
            temp.remove(index);
        }
        return individual;
    }

    public static Individual createGreedyIndividual(TSPCoordinates tspCoordinates, int startingPoint) {
        Individual individual = new Individual();
        List<TSPCoordinate> coords = new ArrayList<>(tspCoordinates.coords);
        individual.citiesIndexes.add(startingPoint);
        TSPCoordinate currentCoord = coords.get(startingPoint);
        Set<Integer> collectedIndexes = new HashSet<>();
        collectedIndexes.add(startingPoint);
        while (individual.citiesIndexes.size() < coords.size()) {
            int minIndex = 0;
            double minDistance = Double.MAX_VALUE;
            for (int i = 0; i < coords.size(); i++) {
                if (collectedIndexes.contains(i)) {
                    continue;
                }
                double v = currentCoord.calculateDistance(tspCoordinates.type, coords.get(i));
                if (v < minDistance) {
                    minIndex = i;
                    minDistance = v;
                }
            }
            individual.citiesIndexes.add(minIndex);
            collectedIndexes.add(minIndex);
            currentCoord = coords.get(minIndex);
        }
        return individual;
    }

    public double calculateRoute(TSPCoordinates tspCoordinates) {
        if(route!= -1){
            return route;
        }
        double sum = 0;
        for (int i = 0; i < citiesIndexes.size()-1; i++) {
            sum+=tspCoordinates.calculateDistance(citiesIndexes.get(i), citiesIndexes.get(i+1));
        }
        route = sum;
        return sum;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Individual individual = new Individual();
        individual.citiesIndexes = new ArrayList<>(this.citiesIndexes);
        return individual;
    }

    private Individual() {

    }

}
