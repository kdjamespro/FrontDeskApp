package com.frontdeskapplication.datasource;

import com.frontdeskapplication.domain.BoxSize;
import com.frontdeskapplication.domain.Customer;
import com.frontdeskapplication.domain.StorageArea;
import com.frontdeskapplication.domain.StorageRecord;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public abstract class Facility {
    private final List<StorageRecord> storageRecords;
    private final List<Customer> customers;
    private final Map<BoxSize, StorageArea> storageAreaMap;
    private final String name;

    protected Facility(String name){
        this.name = name;
        storageRecords = new ArrayList<>();
        customers = new ArrayList<>();
        storageAreaMap = new EnumMap<>(BoxSize.class);

        StorageArea small = new StorageArea(BoxSize.SMALL, 46);
        StorageArea medium = new StorageArea(BoxSize.MEDIUM, 14);
        StorageArea large = new StorageArea(BoxSize.LARGE, 12);

        storageAreaMap.put(small.getBoxSize(), small);
        storageAreaMap.put(medium.getBoxSize(), medium);
        storageAreaMap.put(large.getBoxSize(), large);
    }

    public List<StorageRecord> getStorageRecords() {
        return storageRecords;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public Map<BoxSize, StorageArea> getStorageAreaMap() {
        return storageAreaMap;
    }

    public String getName() {
        return name;
    }
}
