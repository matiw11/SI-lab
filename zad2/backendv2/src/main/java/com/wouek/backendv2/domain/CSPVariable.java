package com.wouek.backendv2.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CSPVariable implements Serializable, Cloneable {
    public static Set<Integer> SUDOKU_DOMAIN = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean removeFromDomain(int i) {
        return domain.remove(i);
    }
    public boolean domainContains(int i ){
        return domain.contains(i);
    }

    public boolean addToDomain(int i) {
        return domain.add(i);
    }

    public Set<Integer> getDomain() {
        return domain;
    }

    @Override
    public CSPVariable clone() {
        CSPVariable clone = new CSPVariable();
        clone.domain = new HashSet<>(this.domain);
        clone.value = this.value;
        return clone;

    }

    private int value;
    private Set<Integer> domain;

    public CSPVariable() {
        domain = new HashSet<>();
        for (Integer i : SUDOKU_DOMAIN) {
            domain.add(i.intValue());
        }
    }
    /*@Override
    protected Object clone(){
        return null;
    }*/
}
