package com.mwolczecki.backend.platform;

import com.mwolczecki.backend.platform.crossing.CrossingAlgorithm;
import com.mwolczecki.backend.platform.crossing.OrderedCrossover;
import com.mwolczecki.backend.platform.initializer.Initializer;
import com.mwolczecki.backend.platform.mutation.InversionMutation;
import com.mwolczecki.backend.platform.mutation.MutationAlogirthm;
import com.mwolczecki.backend.platform.selection.SelectionAlgorithm;
import com.mwolczecki.backend.platform.selection.TournamentSelection;

import java.util.ArrayList;
import java.util.Collections;
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
        tspProblem.pop_size= 100;
        tspProblem.generations = 1000;
        tspProblem.Px = 0.7;
        tspProblem.PM = 0.2;
        tspProblem.tspCoordinates = new TSPParser().parse("kroA200.tsp");
        tspProblem.run();
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
        for(int i =0;i<selectedIndividuals.size();i++){
            int randomIndex = (int) (Math.random()*selectedIndividuals.size());
            if(Math.random()<= Px){
                crossedIndividuals.add(crossingAlgorithm.cross(selectedIndividuals.get(randomIndex), selectedIndividuals.get(i)));
            }else{
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
