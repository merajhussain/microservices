package com.microservices.orderservice.service;

import com.microservices.orderservice.dto.OrderLineItemDto;
import com.microservices.orderservice.dto.OrderRequest;
import com.microservices.orderservice.model.Order;
import com.microservices.orderservice.model.OrderLineItem;
import com.microservices.orderservice.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Service
@Slf4j
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
  WebClient webClient;

    public void placeOrder(OrderRequest orderRequest){

        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());



        var itemnsList=orderRequest.getItems()
                .stream()
                .map(this::maptoDto)
                .toList();

     order.setItems(itemnsList);

        Boolean result= webClient.get()
                .uri("http://localhost:8082/api/inventory/12345")
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
        if(result){
            try{
                orderRepository.save(order);
            }catch (Exception e){
                log.error("Error saving order");
            }
        }




    }

    private OrderLineItem maptoDto(OrderLineItemDto orderLineItemDto) {

          OrderLineItem orderLineItem = new OrderLineItem();
          orderLineItem.setId(orderLineItem.getId());
          orderLineItem.setQuantity(orderLineItem.getQuantity());
          orderLineItem.setSkucode(orderLineItem.getSkucode());
          orderLineItem.setPrice(orderLineItem.getPrice());
          return orderLineItem;
    }

}
