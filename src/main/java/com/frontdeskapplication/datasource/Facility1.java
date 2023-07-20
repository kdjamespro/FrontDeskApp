package com.frontdeskapplication.datasource;

public class Facility1 extends Facility{
    private static final Facility facility1 = new Facility1();

    private Facility1(){
        super("Facility 1");
    }

    public static Facility getInstance() {
        return facility1;
    }
}
