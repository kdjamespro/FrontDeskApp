package com.frontdeskapplication.domain;

import java.util.Objects;

public class Box {
    private static int idCounter = 0;
    private int id;
    private BoxSize boxSize;

    public Box(BoxSize boxSize) {
        id = ++idCounter;
        this.boxSize = boxSize;
    }

    @Override
    public String toString() {
        return "Box{" +
            "id=" + id +
            ", boxSize=" + boxSize +
            '}';
    }

    public BoxSize getBoxSize() {
        return boxSize;
    }

    public void setBoxSize(BoxSize boxSize) {
        this.boxSize = boxSize;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Box box = (Box) o;
        return getId() == box.getId() && getBoxSize() == box.getBoxSize();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBoxSize());
    }
}
