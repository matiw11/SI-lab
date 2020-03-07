package com.mwolczecki.backend.platform.initializer;

import com.mwolczecki.backend.platform.Individual;
import com.mwolczecki.backend.platform.TSPCoordinates;

public interface Initializer {

    Individual initialize(TSPCoordinates tspCoordinates);
}
