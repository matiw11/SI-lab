package com.mwolczecki.backend.platform;

import com.mwolczecki.backend.platform.crossing.CrossingAlgorithm;
import com.mwolczecki.backend.platform.crossing.CycleCrossover;
import com.mwolczecki.backend.platform.crossing.OrderedCrossover;
import com.mwolczecki.backend.platform.crossing.PartiallyMatchedCrossover;
import com.mwolczecki.backend.platform.mutation.InversionMutation;
import com.mwolczecki.backend.platform.mutation.MutationAlogirthm;
import com.mwolczecki.backend.platform.mutation.SwapMutation;
import com.mwolczecki.backend.platform.selection.RouletteSelection;
import com.mwolczecki.backend.platform.selection.SelectionAlgorithm;
import com.mwolczecki.backend.platform.selection.TournamentSelection;

import java.util.Map;

public class TSPProblemFactory {

    public static TSPProblem build(Map<String, String> map) {
        String crossingAlgorithmString = map.get("crossingAlgorithm");
        String mutationAlgorithmString = map.get("mutationAlgorithm");
        String selectionAlgorithmString = map.get("selectionAlgorithm");
        int tour = Integer.parseInt(map.get("tour"));
        int pop_size = Integer.parseInt(map.get("pop_size"));
        int generations = Integer.parseInt(map.get("generations"));
        double Px = Double.parseDouble(map.get("Px"));
        double Pm = Double.parseDouble(map.get("Pm"));
        String fileName = map.get("fileName")+".tsp";
        TSPCoordinates coordinates = new TSPParser().parse(fileName);
        CrossingAlgorithm crossingAlgorithm = getCrossingAlgorithm(crossingAlgorithmString);
        MutationAlogirthm mutationAlogirthm = getMutationAlgorithm(mutationAlgorithmString);
        SelectionAlgorithm selectionAlgorithm = getSelectionAlgorithm(selectionAlgorithmString, tour);

        TSPProblem tspProblem = new TSPProblem();
        tspProblem.crossingAlgorithm = crossingAlgorithm;
        tspProblem.mutationAlogirthm = mutationAlogirthm;
        tspProblem.selectionAlgorithm = selectionAlgorithm;
        tspProblem.pop_size= pop_size;
        tspProblem.generations = generations;
        tspProblem.Px = Px;
        tspProblem.PM = Pm;
        tspProblem.tspCoordinates = coordinates;
        return tspProblem;
    }

    private static SelectionAlgorithm getSelectionAlgorithm(String selectionAlgorithmString, int tour) {
        switch (selectionAlgorithmString) {
            case "TOUR":
                return new TournamentSelection(tour);
            case "ROUL":
                return new RouletteSelection();
            default:
                return new TournamentSelection(tour);
        }

    }

    private static MutationAlogirthm getMutationAlgorithm(String mutationAlgorithmString) {
        switch (mutationAlgorithmString) {
            case "SWAP":
                return new SwapMutation();
            case "INVERSE":
                return new InversionMutation();
            default:
                return new InversionMutation();
        }

    }

    private static CrossingAlgorithm getCrossingAlgorithm(String crossingAlgorithmString) {
        switch (crossingAlgorithmString) {
            case "OX":
                return new OrderedCrossover();
            case "CX":
                return new CycleCrossover();
            case "PMX":
                return new PartiallyMatchedCrossover();
            default:
                return new OrderedCrossover();
        }
    }
}
