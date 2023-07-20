package com.frontdeskapplication.datasource;

public class Facility3 extends Facility{
    private static final Facility facility3 = new Facility3();

    private Facility3(){
        super("Facility 3");
    }

    public static Facility getInstance() {
        return facility3;
    }
}
