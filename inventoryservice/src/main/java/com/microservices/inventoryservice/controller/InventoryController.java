package com.microservices.inventoryservice.controller;

import com.microservices.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/inventory")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @GetMapping("/{skucode}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInstock(@PathVariable String skucode){

         return inventoryService.isInStock(skucode);
    }
}
