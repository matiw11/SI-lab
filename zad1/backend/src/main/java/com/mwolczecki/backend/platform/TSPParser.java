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

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


public class TSPParser {
    private int fileType = 0;
    public static final int EUC_2D = 1;
    public static final int GEO = 2;

    public static void main(String[] args) {
        TSPParser tspParser = new TSPParser();
        TSPCoordinates parse = tspParser.parse("berlin11_modified.tsp");
        int fileType = tspParser.fileType;
        Individual greedyIndividual1 = Individual.createRandomIndividual(11);
        System.out.println(greedyIndividual1.citiesIndexes);
        System.out.println(greedyIndividual1.calculateRoute(parse));


        Individual greedyIndividual2 = Individual.createRandomIndividual(11);
        System.out.println(greedyIndividual2.citiesIndexes);
        System.out.println(greedyIndividual2.calculateRoute(parse));
        Individual greedyIndividual = Individual.createRandomIndividual(11);
        System.out.println(greedyIndividual.citiesIndexes);
        System.out.println(greedyIndividual.calculateRoute(parse));

        List<Individual> individuals = List.of(greedyIndividual1, greedyIndividual2, greedyIndividual);
        SelectionAlgorithm algorithm = new RouletteSelection();
        Individual select = algorithm.select(individuals, parse);
        System.out.println(select.citiesIndexes);


    }

    public TSPCoordinates parse(String fileName) {

        List<TSPCoordinate> coords = new ArrayList<>();
        try {
            InputStream resourceAsStream = TSPParser.class.getClassLoader().getResourceAsStream("dane/TSP/" + fileName);
            BufferedReader in = new BufferedReader(new InputStreamReader(resourceAsStream));
            String line;
            boolean nodeCoordSection = false;

            while ((line = in.readLine()) != null) {
                if (!line.equalsIgnoreCase("EOF") && !line.equalsIgnoreCase(" EOF") && !line.equals("")) {
                    if (!line.equalsIgnoreCase("NODE_COORD_SECTION") && !nodeCoordSection) {
                        if (line.replace(" ", "").equalsIgnoreCase("EDGE_WEIGHT_TYPE:EUC_2D"))
                            fileType = EUC_2D;
                        else if (line.replace(" ", "").equalsIgnoreCase("EDGE_WEIGHT_TYPE:GEO"))
                            fileType = GEO;
                    } else if (line.equalsIgnoreCase("NODE_COORD_SECTION")) {
                        nodeCoordSection = true;
                    } else { // All the numbers are in this part

                        StringTokenizer strTok = new StringTokenizer(line, " \t");
                        try {

                            strTok.nextToken(); // Discard the city number
                            if (fileType == EUC_2D || fileType == GEO) {
                                double x = Double.valueOf(strTok.nextToken()).doubleValue();
                                double y = Double.valueOf(strTok.nextToken()).doubleValue();
                                coords.add(new TSPCoordinate(x, y));
                            }
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new TSPCoordinates(coords, fileType);
    }

    public int getFileType() {
        return fileType;
    }
}
