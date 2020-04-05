package com.kochamWiktorie.backend.platform;

import com.kochamWiktorie.backend.platform.initializer.GreedyInitializer;
import com.kochamWiktorie.backend.platform.initializer.Initializer;
import com.kochamWiktorie.backend.platform.initializer.RandomInitializer;
import com.kochamWiktorie.backend.platform.mutation.MutationAlogirthm;
import com.kochamWiktorie.backend.platform.crossing.CrossingAlgorithm;
import com.kochamWiktorie.backend.platform.crossing.OrderedCrossover;
import com.kochamWiktorie.backend.platform.mutation.InversionMutation;
import com.kochamWiktorie.backend.platform.selection.SelectionAlgorithm;
import com.kochamWiktorie.backend.platform.selection.TournamentSelection;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TSPProblem {
    CrossingAlgorithm crossingAlgorithm;
    MutationAlogirthm mutationAlogirthm;
    SelectionAlgorithm selectionAlgorithm;
    int pop_size;
    int generations;
    double Px;
    double PM;
    TSPCoordinates tspCoordinates;
    private List<Evaluation> evaluations = new CopyOnWriteArrayList<>();
    Initializer initializer;

    public static void main(String[] args) {
        TSPProblem tspProblem = new TSPProblem();
        tspProblem.crossingAlgorithm = new OrderedCrossover();
        tspProblem.mutationAlogirthm = new InversionMutation();
        tspProblem.selectionAlgorithm = new TournamentSelection(5);
        tspProblem.initializer = new GreedyInitializer();
        GreedyInitializer greedyInitializer = new GreedyInitializer();
        tspProblem.pop_size = 100;
        tspProblem.generations = 1000;
        tspProblem.Px = 0.7;
        tspProblem.PM = 0.2;
        tspProblem.tspCoordinates = new TSPParser().parse("berlin53.tsp");
        double min = Double.MAX_VALUE;
        for (int i = 0; i < tspProblem.tspCoordinates.coords.size(); i++) {
            Individual initialize = greedyInitializer.initialize(tspProblem.tspCoordinates, i);
            double v = initialize.calculateRoute(tspProblem.tspCoordinates);
            if (v < min) {
                min = v;
            }
        }
        System.out.println("Greedy min: " + min);
        min = Double.MAX_VALUE;
        RandomInitializer randomInitializer = new RandomInitializer();
        for (int i = 0; i < 1000; i++) {
            Individual initialize = randomInitializer.initialize(tspProblem.tspCoordinates);
            double v = initialize.calculateRoute(tspProblem.tspCoordinates);
            if (v < min) {
                min = v;
            }
        }
        System.out.println("Random min: " + min);
       // tspProblem.run();
        // System.out.println(tspProblem.evaluations);

    }

    public List<Evaluation> getEvaluations() {
        return evaluations;

    }

    public List<Evaluation> run() {
        evaluations = new CopyOnWriteArrayList<>();
        List<Individual> population = initialize();
        Evaluation evaluation1 = evaluate(population);
        evaluations.add(evaluation1);
        for (int i = 1; i < generations; i++) {
            List<Individual> selectedIndividuals = select(population);
            List<Individual> crossedIndividuals = cross(selectedIndividuals);
            List<Individual> mutatedIndividuals = mutate(crossedIndividuals);

            Evaluation evaluate = evaluate(mutatedIndividuals);
            // System.out.println(evaluate);
            evaluations.add(evaluate);
            population = crossedIndividuals;
        }
        return evaluations;
    }

    private List<Individual> cross(List<Individual> selectedIndividuals) {
        List<Individual> crossedIndividuals = new ArrayList<>();
        for (int i = 0; i < selectedIndividuals.size(); i++) {
            int randomIndex = (int) (Math.random() * selectedIndividuals.size());
            if (Math.random() <= Px) {
                crossedIndividuals.add(crossingAlgorithm.cross(selectedIndividuals.get(randomIndex), selectedIndividuals.get(i)));
            } else {
                crossedIndividuals.add(selectedIndividuals.get(i));
            }
        }
        return crossedIndividuals;
    }

    private List<Individual> mutate(List<Individual> population) {
        List<Individual> mutatedList = new ArrayList<>();
        for (Individual individual : population) {
            if (Math.random() <= PM)
                mutatedList.add(mutationAlogirthm.mutate(individual));
            else
                mutatedList.add(individual);
        }
        return mutatedList;
    }

    public List<Individual> select(List<Individual> population) {
        List<Individual> list = new ArrayList<>();
        for (int j = 0; j < pop_size; j++) {
            list.add(selectionAlgorithm.select(population, tspCoordinates));
        }
        return list;
    }

    private List<Individual> initialize() {
        List<Individual> population = new ArrayList<>();
        for (int i = 0; i < pop_size; i++) {
            population.add(initializer.initialize(tspCoordinates));
        }
        return population;
    }

    private Evaluation evaluate(List<Individual> population) {
        double average = 0;
        double min = Double.MAX_VALUE;
        double max = 0;

        for (Individual individual : population) {
            Double route = individual.calculateRoute(tspCoordinates);
            average += route;
            if (route < min) {
                min = route;
                // System.out.println(individual.citiesIndexes);
            }
            if (route > max) {
                max = route;
            }
        }
        average /= pop_size;
        return new Evaluation(max, average, min);
    }
}
