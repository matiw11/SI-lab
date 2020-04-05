package com.kochamWiktorie.backend.platform.initializer;

import com.kochamWiktorie.backend.platform.Individual;
import com.kochamWiktorie.backend.platform.TSPCoordinates;

public interface Initializer {

    Individual initialize(TSPCoordinates tspCoordinates);
}
