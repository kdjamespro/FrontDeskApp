package com.frontdeskapplication.service;

import com.frontdeskapplication.domain.Box;
import com.frontdeskapplication.domain.BoxSize;
import com.frontdeskapplication.repository.StorageRepository;
import java.util.Map;
import java.util.Map.Entry;

public class StorageService {
    private final StorageRepository storageRepository;

    public StorageService(){
        storageRepository = new StorageRepository();
    }

    public Box store(BoxSize boxSize){
        Box box = new Box(boxSize);
        storageRepository.store(box);
        return box;
    }

    public int getRemainingCapacityCount(BoxSize boxSize){
        return storageRepository.getRemainingCapacity(boxSize);
    }

    public int getMaxCapacityCount(BoxSize boxSize){
        return storageRepository.getMaxCapacity(boxSize);
    }

    public boolean isAvailable(BoxSize boxSize){
        return storageRepository.isAvailable(boxSize);
    }

    public Box retrieve(int id){
        return storageRepository.findBoxById(id);
    }

    public void displayOtherFacilities(){
        Map<String, int[]> countMap = storageRepository.getOtherFacilitiesCapacity();
        if(countMap.isEmpty()){
            System.out.println("No other facilities");
            return;
        }
        for(Entry<String, int[]> entry : countMap.entrySet()){
            System.out.println();
            System.out.println(entry.getKey());
            System.out.println("Available storage areas:");
            System.out.println("SMALL AREA: " + entry.getValue()[0] + " slots available");
            System.out.println("MEDIUM AREA: " + entry.getValue()[1] + " slots available");
            System.out.println("LARGE AREA: " + entry.getValue()[2] + " slots available");
            System.out.println();
        }
    }

}
