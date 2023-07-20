package com.frontdeskapplication.repository;

import com.frontdeskapplication.datasource.Facility;
import com.frontdeskapplication.datasource.Facility1;
import com.frontdeskapplication.datasource.Facility2;
import com.frontdeskapplication.datasource.Facility3;
import com.frontdeskapplication.domain.Box;
import com.frontdeskapplication.domain.BoxSize;
import com.frontdeskapplication.domain.StorageArea;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StorageRepository {
    private final Map<BoxSize, StorageArea> storageAreaMap;
    private static final List<Facility> facilities = Arrays.asList(Facility1.getInstance(), Facility2.getInstance(),
        Facility3.getInstance());

    public StorageRepository(){
        storageAreaMap = facilities.get(0).getStorageAreaMap();
    }

    public void store(Box box){
        StorageArea storageArea = storageAreaMap.get(box.getBoxSize());
        storageArea.addBox(box);
    }

    public int getRemainingCapacity(BoxSize boxSize){
        return storageAreaMap.get(boxSize).getRemainingCapacity();
    }

    public int getMaxCapacity(BoxSize boxSize){
        return storageAreaMap.get(boxSize).getMaxCapacity();
    }

    public boolean isAvailable(BoxSize boxSize){
        return storageAreaMap.get(boxSize).getRemainingCapacity() != 0;
    }
    public Box removeBox(Box box){
        return storageAreaMap.get(box.getBoxSize()).removeBox(box.getId());
    }

    public Box findBoxById(int id){
        Box box = findBox(id);
        if(box == null){
            return null;
        }

        return storageAreaMap.get(box.getBoxSize()).removeBox(box.getId());
    }

    public Map<String, int[]> getOtherFacilitiesCapacity(){
        Map<String, int[]> map = new HashMap<>();
        for(int i = 1 ; i < facilities.size(); i++){
            int[] values = new int[3];
            Facility facility = facilities.get(i);
            values[0] = facility.getStorageAreaMap().get(BoxSize.SMALL).getRemainingCapacity();
            values[1] = facility.getStorageAreaMap().get(BoxSize.MEDIUM).getRemainingCapacity();
            values[2] = facility.getStorageAreaMap().get(BoxSize.LARGE).getRemainingCapacity();
            map.put(facility.getName(), values);
        }

        return map;
    }

    private Box findBox(int id){
        Box box = storageAreaMap.get(BoxSize.SMALL).findById(id);
        if(box != null){
            return box;
        }

        box = storageAreaMap.get(BoxSize.MEDIUM).findById(id);
        if(box != null){
            return box;
        }

        return storageAreaMap.get(BoxSize.LARGE).findById(id);
    }

}
