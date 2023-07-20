package com.frontdeskapplication.service;

import com.frontdeskapplication.domain.Box;
import com.frontdeskapplication.domain.Customer;
import com.frontdeskapplication.repository.CustomerRepository;
import java.util.List;

public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(){
        customerRepository = new CustomerRepository();
    }
    public Customer createCustomer(String firstName, String lastName, String phoneNumber){
        Customer customer = new Customer(firstName, lastName, phoneNumber);
        customerRepository.addCustomer(customer);
        return customer;
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.getCustomers();
    }

    public Customer findCustomerById(int id){
        return customerRepository.findCustomerById(id);
    }

    public void storeBox(Customer customer, Box box){
        customerRepository.storeBox(customer, box);
    }
}
