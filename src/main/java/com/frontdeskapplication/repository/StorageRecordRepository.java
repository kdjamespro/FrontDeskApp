package com.frontdeskapplication.repository;

import com.frontdeskapplication.datasource.Facility;
import com.frontdeskapplication.datasource.Facility1;
import com.frontdeskapplication.domain.StorageRecord;
import java.util.ArrayList;
import java.util.List;

public class StorageRecordRepository {
    private final List<StorageRecord> storageRecords;
    private static final Facility facility = Facility1.getInstance();
    public StorageRecordRepository(){

        storageRecords = facility.getStorageRecords();
    }

    public void addRecord(StorageRecord storageRecord){
        storageRecords.add(storageRecord);
    }

    public List<StorageRecord> getStorageRecords(){
        return new ArrayList<>(storageRecords);
    }
}
