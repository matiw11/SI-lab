package com.mwolczecki.backend.platform;
import static com.mwolczecki.backend.platform.TSPParser.*;
public class TSPCoordinate {

    private double x;
    private double y;

    public TSPCoordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() { return this.x; }

    public double calculateDistance(int type, TSPCoordinate otherCoordinate) {
        if( type == EUC_2D ) {
            double dX = x - otherCoordinate.x;
            double dY = y - otherCoordinate.y;
            return (int) Math.round( Math.sqrt( dX*dX + dY*dY ) );
        } else if( type == GEO ) {
            double deg = Math.floor( x );
            double min = x - deg;
            double latitudeI = Math.PI * ( deg + 5.0 * min / 3.0 ) / 180.0;

            deg = Math.floor( y );
            min = y - deg;
            double longitudeI = Math.PI * ( deg + 5.0 * min / 3.0 ) / 180.0;

            deg = Math.floor( otherCoordinate.x );
            min = otherCoordinate.x - deg;
            double latitudeJ = Math.PI * ( deg + 5.0 * min / 3.0 ) / 180.0;

            deg = Math.floor( otherCoordinate.y );
            min = otherCoordinate.y - deg;
            double longitudeJ = Math.PI * ( deg + 5.0 * min / 3.0 ) / 180.0;

            double RRR = 6378.388;
            double q1 = Math.cos( longitudeI - longitudeJ );
            double q2 = Math.cos( latitudeI - latitudeJ );
            double q3 = Math.cos( latitudeI + latitudeJ );
            return (int) Math.round( (RRR*Math.acos(0.5*((1.0+q1)*q2-(1.0-q1)*q3))+1.0) );
        }
        return 0;
    }

    @Override
    public String toString() {
        return "TSPCoordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public double getY() { return this.y; }
}
