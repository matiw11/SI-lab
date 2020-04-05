package com.kochamWiktorie.backend.platform;

import java.util.ArrayList;
import java.util.List;

public class TSPCoordinates {
    public List<TSPCoordinate> coords = new ArrayList<>();
    public int type = 0;

    public TSPCoordinates(List<TSPCoordinate> coords, int type) {
        this.coords = coords;
        this.type = type;
    }

    public double calculateDistance(int first, int second) {

        return coords.get(first).calculateDistance(type, coords.get(second));


    }

}
