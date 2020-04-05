package com.kochamWiktorie.backend.platform.selection;

import com.kochamWiktorie.backend.platform.Individual;
import com.kochamWiktorie.backend.platform.TSPCoordinates;

import java.util.List;

public interface SelectionAlgorithm {

    Individual select(List<Individual> individuals, TSPCoordinates coordinates);
}
