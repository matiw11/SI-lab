package com.kochamWiktorie.backend.platform.crossing;

import com.kochamWiktorie.backend.platform.Individual;

public interface CrossingAlgorithm {

    Individual cross(Individual firstIndividual, Individual secondIndividual);
}
