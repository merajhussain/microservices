package com.microservices.inventoryservice.service;

import com.microservices.inventoryservice.InventoryRepository;
import com.microservices.inventoryservice.model.Inventory;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    @PostConstruct
    void loadData(){
        Inventory inventory =  new Inventory();
        inventory.setQuantity(1);
        inventory.setSkucode("12345");
         inventoryRepository.save(inventory);


    }

    @PreDestroy
    void clearData(){
        inventoryRepository.deleteAll();
    }

    @Transactional
    public boolean isInStock(String skucode){

        return inventoryRepository.findByskucode(skucode).isPresent();

    }
}
