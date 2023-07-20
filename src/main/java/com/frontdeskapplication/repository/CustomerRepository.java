package com.frontdeskapplication.repository;

import com.frontdeskapplication.datasource.Facility;
import com.frontdeskapplication.datasource.Facility1;
import com.frontdeskapplication.domain.Box;
import com.frontdeskapplication.domain.Customer;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    private final List<Customer> customers;
    private static final Facility facility = Facility1.getInstance();

    public CustomerRepository(){
        customers = facility.getCustomers();
    }

    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    public List<Customer> getCustomers(){
        return new ArrayList<>(customers);
    }

    public Customer findCustomerById(int id){
        return customers.stream()
            .filter(customer -> customer.getId() == id)
            .findFirst()
            .orElse(null);
    }

    public void storeBox(Customer customer, Box box){
        customer.storeBox(box);
    }
}
