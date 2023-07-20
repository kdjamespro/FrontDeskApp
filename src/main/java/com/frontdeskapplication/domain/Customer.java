package com.frontdeskapplication.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer {
    private static int idCounter = 0;
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private final List<Box> storedBoxes;

    public Customer(String firstName, String lastName, String phoneNumber) {
        id = ++idCounter;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        storedBoxes = new ArrayList<>();
    }

    public Customer(Customer customer){
        this.id = customer.getId();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.phoneNumber = customer.getPhoneNumber();
        storedBoxes = customer.getStoredBoxes();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Box> getStoredBoxes(){
        return new ArrayList<>(storedBoxes);
    }

    public void storeBox(Box box){
        storedBoxes.add(box);
    }

    public void removeBox(Box box){
        storedBoxes.remove(box);
    }

    @Override
    public String toString() {
        return "Customer{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", storedBoxes=" + storedBoxes +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer customer = (Customer) o;
        return getId() == customer.getId() && getFirstName().equals(customer.getFirstName()) && getLastName().equals(
            customer.getLastName()) && getPhoneNumber().equals(customer.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getPhoneNumber());
    }
}
