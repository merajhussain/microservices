package com.microservices.inventoryservice;

import com.microservices.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    Optional<Inventory> findByskucode(String skucode);
}
