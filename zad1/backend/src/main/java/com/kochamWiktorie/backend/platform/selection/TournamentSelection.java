package com.kochamWiktorie.backend.platform.selection;

import com.kochamWiktorie.backend.platform.Individual;
import com.kochamWiktorie.backend.platform.TSPCoordinates;

import java.util.ArrayList;
import java.util.List;

public class TournamentSelection implements SelectionAlgorithm {
    private final int N;

    public TournamentSelection(int n) {
        N = n;
    }

    @Override
    public Individual select(List<Individual> individuals, TSPCoordinates coordinates) {
        List<Integer> choosenIndividualsIndexes = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            choosenIndividualsIndexes.add((int) (Math.random() * individuals.size()));
        }
        int choosenIndex = choosenIndividualsIndexes.get(0);
        Individual individual1 = individuals.get(choosenIndex);
        double minDistance = individual1.calculateRoute(coordinates);
        for (int i = 1; i < choosenIndividualsIndexes.size(); i++) {
            int individualIndex = choosenIndividualsIndexes.get(i);
            Individual individual = individuals.get(individualIndex);
            double distance = individual.calculateRoute(coordinates);
            if (distance < minDistance) {
                choosenIndex = individualIndex;
                minDistance = distance;
            }
        }
        try {
            return (Individual) individuals.get(choosenIndex).clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
