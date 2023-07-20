package com.frontdeskapplication.domain;

public enum BoxSize {
    SMALL("Small"),
    MEDIUM("Medium"),
    LARGE("Large");

    public final String label;

    private BoxSize(String label) {
        this.label = label;
    }
}
