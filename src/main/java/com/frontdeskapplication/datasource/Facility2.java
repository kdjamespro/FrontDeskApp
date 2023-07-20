package com.frontdeskapplication.datasource;

public class Facility2 extends Facility{
    private static final Facility facility2 = new Facility2();

    private Facility2(){
        super("Facility 2");
    }

    public static Facility getInstance() {
        return facility2;
    }

}
