package com.frontdeskapplication.service;

import com.frontdeskapplication.domain.Box;
import com.frontdeskapplication.domain.Customer;
import com.frontdeskapplication.domain.StorageRecord;
import com.frontdeskapplication.repository.StorageRecordRepository;
import java.util.List;

public class StorageRecordService {
    private final StorageRecordRepository storageRecordRepository;

    public StorageRecordService(){
        storageRecordRepository = new StorageRecordRepository();
    }

    public StorageRecord addRecord(Customer customer, Box box, boolean stored){
        StorageRecord storageRecord = new StorageRecord(new Customer(customer), box, stored);
        storageRecordRepository.addRecord(storageRecord);
        return storageRecord;
    }

    public List<StorageRecord> getAllStorageRecords(){
        return storageRecordRepository.getStorageRecords();
    }
}
