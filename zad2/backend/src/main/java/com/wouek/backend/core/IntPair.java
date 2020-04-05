package com.wouek.backend.core;

import java.util.Objects;

public class IntPair {
    public int x;
    public int y;

    public IntPair() {
    }

    public IntPair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntPair)) return false;
        IntPair intPair = (IntPair) o;
        return x == intPair.x &&
                y == intPair.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "IntPair{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
