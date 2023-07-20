package com.frontdeskapplication.view;

import com.frontdeskapplication.domain.Box;
import com.frontdeskapplication.domain.BoxSize;
import com.frontdeskapplication.domain.Customer;
import com.frontdeskapplication.domain.StorageRecord;
import com.frontdeskapplication.service.CustomerService;
import com.frontdeskapplication.service.StorageRecordService;
import com.frontdeskapplication.service.StorageService;
import java.util.List;
import java.util.Scanner;

public class ConsoleGUI {
    private final CustomerService customerService;
    private final StorageService storageService;
    private final StorageRecordService storageRecordService;
    private final Scanner scanner;

    public ConsoleGUI() {
        this.customerService = new CustomerService();
        this.storageService = new StorageService();
        this.storageRecordService = new StorageRecordService();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        displayWelcomeMessage();
        int choice;
        do {
            displayMenu();
            choice = getUserChoice();

            switch (choice) {
                case 1:
                    System.out.println();
                    createCustomer();
                    break;
                case 2:
                    System.out.println();
                    acceptOrReject();
                    break;
                case 3:
                    System.out.println();
                    retrieveBox();
                    break;
                case 4:
                    System.out.println();
                    displayAvailableAreas();
                    break;
                case 5:
                    System.out.println();
                    displayStorageRecords();
                    break;
                case 6:
                    System.out.println();
                    displayAllCustomers();
                    break;
                case 7:
                    displayOtherFacilities();
                    break;
                case 8:
                    System.out.println("Thank you for using FrontDeskApp!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 8);
    }

    private void displayWelcomeMessage() {
        System.out.println("Welcome to FrontDeskApp!");
    }

    private void displayMenu() {
        System.out.println();
        System.out.println("Please select an option:");
        System.out.println("1. Create a new customer");
        System.out.println("2. Accept or Reject box");
        System.out.println("3. Retrieve box");
        System.out.println("4. Display available storage areas");
        System.out.println("5. Display storage records");
        System.out.println("6. Display all customers");
        System.out.println("7. Display other facilities available storage areas");
        System.out.println("8. Exit");
    }

    private int getUserChoice() {
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    private void createCustomer() {
        System.out.print("Enter customer's first name: ");
        String firstName = scanner.next();

        System.out.print("Enter customer's last name: ");
        String lastName = scanner.next();

        System.out.print("Enter customer's phone number: ");
        String phoneNumber = scanner.next();

        Customer customer = customerService.createCustomer(firstName, lastName, phoneNumber);
        System.out.println("Customer #" + customer.getId() + " " + customer.getFirstName() + " " + customer.getLastName() + " was added successfully!");
    }

    private Customer findCustomerById(){
        System.out.print("Enter customer id: ");
        int customerId = scanner.nextInt();
        Customer customer = customerService.findCustomerById(customerId);
        return customer;
    }

    private BoxSize getBoxSize(){
        System.out.print("Enter box size ((1) Small, (2) Medium, or (3) Large): ");
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                return BoxSize.SMALL;
            case 2:
                return BoxSize.MEDIUM;
            case 3:
                return BoxSize.LARGE;
            default:
                System.out.println("Please enter a valid choice. Returning to main menu...");
        }
        return null;
    }
    private void acceptOrReject() {
        Customer customer = findCustomerById();
        if (customer == null){
            System.out.println("Customer not found. Please enter a valid customer id.");
            return;
        }

        BoxSize boxSize = getBoxSize();
        if(boxSize == null){
            return;
        }

        storageService.isAvailable(boxSize);
        if(storageService.isAvailable(boxSize)){
            Box box = storageService.store(boxSize);
            customerService.storeBox(customer, box);
            StorageRecord storageRecord = storageRecordService.addRecord(customer, box, true);
            System.out.println(storageRecord.summarizeRecord());
        }
        else{
            System.out.println("Sorry! We cannot accept " +  boxSize.label + " box since our storage is already full");
        }
    }

    private void retrieveBox(){
        Customer customer = findCustomerById();
        if (customer == null){
            System.out.println("Customer not found. Please enter a valid customer id.");
            return;
        }
        List<Box> customerBoxes = customer.getStoredBoxes();
        if(customerBoxes.isEmpty()){
            System.out.println("No boxes stored for customer " + customer.getFirstName() + " " + customer.getLastName());
            return;
        }
        System.out.println("Stored box(es) for customer: ");
        for(Box box : customerBoxes){
            System.out.println(box.toString());
        }
        System.out.println("Select the id of the box you want to retrieve: ");
        int id = scanner.nextInt();
        Box retreivedBox = storageService.retrieve(id);
        if(retreivedBox == null){
            System.out.println("The box the you selected does not belong to the customer or do not exist");
            return;
        }
        customer.removeBox(retreivedBox);
        StorageRecord storageRecord = storageRecordService.addRecord(customer, retreivedBox, false);
        System.out.println(storageRecord.summarizeRecord());
    }

    private void displayAvailableAreas() {
        System.out.println();
        System.out.println("Available storage areas:");
        System.out.println("SMALL AREA: " + storageService.getRemainingCapacityCount(BoxSize.SMALL) + " slots available");
        System.out.println("MEDIUM AREA: " + storageService.getRemainingCapacityCount(BoxSize.MEDIUM) + " slots available");
        System.out.println("LARGE AREA: " + storageService.getRemainingCapacityCount(BoxSize.LARGE) + " slots available");
        System.out.println();
    }

    private void displayStorageRecords() {
        List<StorageRecord> storageRecords = storageRecordService.getAllStorageRecords();
        System.out.println();
        System.out.println("Storage Records:");
        for(StorageRecord storageRecord : storageRecords){
            System.out.println(storageRecord.toString());
        }
    }

    private void displayAllCustomers(){
        List<Customer> customers = customerService.getAllCustomers();
        System.out.println();
        System.out.println("Customers: ");
        for(Customer customer : customers){
            System.out.println(customer.toString());
        }
    }

    private void displayOtherFacilities(){
        storageService.displayOtherFacilities();
    }
}
