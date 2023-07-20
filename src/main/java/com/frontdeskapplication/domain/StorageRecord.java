package com.frontdeskapplication.domain;

public class StorageRecord {
    static int idCounter = 0;
    private int id;
    private Customer customer;
    private Box box;
    private String action;

    public StorageRecord(Customer customer, Box box, boolean stored) {
        this.id = ++idCounter;
        this.customer = customer;
        this.box = box;
        action = stored ? "STORE" : "RETRIEVE";
    }

    @Override
    public String toString() {
        return "StorageRecord{" +
            "id=" + id +
            ", customer=" + customer +
            ", box=" + box +
            ", action='" + action + '\'' +
            '}';
    }

    public String summarizeRecord(){
        return String.format("%s Customer #%d box's (%d) in %s storage area", action, customer.getId(), box.getId(),box.getBoxSize().label);
    }
}
