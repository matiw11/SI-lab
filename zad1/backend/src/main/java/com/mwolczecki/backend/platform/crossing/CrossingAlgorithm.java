package com.mwolczecki.backend.platform.crossing;

import com.mwolczecki.backend.platform.Individual;

public interface CrossingAlgorithm {

    Individual cross(Individual firstIndividual, Individual secondIndividual);
}
