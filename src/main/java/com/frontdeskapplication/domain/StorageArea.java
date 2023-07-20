package com.frontdeskapplication.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class StorageArea {
    private BoxSize boxSize;
    private int maxCapacity;
    private int remainingCapacity;
    private List<Box> storage;
    public StorageArea(BoxSize boxSize, int maxCapacity) {
        this.boxSize = boxSize;
        this.maxCapacity = maxCapacity;
        remainingCapacity = maxCapacity;
        storage = new ArrayList<>(maxCapacity);
    }

    public BoxSize getBoxSize() {
        return boxSize;
    }

    public void setBoxSize(BoxSize boxSize) {
        this.boxSize = boxSize;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getRemainingCapacity() {
        return remainingCapacity;
    }

    public void setRemainingCapacity(int remainingCapacity) {
        this.remainingCapacity = remainingCapacity;
    }

    public void addBox(Box box){
        storage.add(box);
        remainingCapacity -= 1;
    }

    public Box removeBox(int id){
        int index = IntStream.range(0, storage.size())
            .filter(i -> storage.get(i).getId() == id)
            .findFirst()
            .orElse(-1);

        if(index == -1){
            return null;
        }

        int lastElementIndex = storage.size() - 1;
        swap(index, lastElementIndex);

        Box removed = storage.remove(lastElementIndex);
        remainingCapacity += 1;

        return removed;
    }

    private void swap(int i, int j){
        Box boxTobeRemoved = storage.get(i);
        Box lastBox = storage.get(j);

        storage.set(i, lastBox);
        storage.set(j, boxTobeRemoved);
    }

    public Box findById(int id){
        return storage.stream()
            .filter(box -> box.getId() == id)
            .findAny()
            .orElse(null);
    }
}
