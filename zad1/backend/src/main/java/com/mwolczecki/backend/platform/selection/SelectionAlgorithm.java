package com.mwolczecki.backend.platform.selection;

import com.mwolczecki.backend.platform.Individual;
import com.mwolczecki.backend.platform.TSPCoordinates;

import java.util.List;

public interface SelectionAlgorithm {

    Individual select(List<Individual> individuals, TSPCoordinates coordinates);
}
