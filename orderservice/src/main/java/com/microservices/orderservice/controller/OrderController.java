package com.microservices.orderservice.controller;

import com.microservices.orderservice.dto.OrderRequest;
import com.microservices.orderservice.service.OrderService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest){

        orderService.placeOrder(orderRequest);
        return "order placed successfully";

    }

}
