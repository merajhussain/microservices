package com.microservices.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemDto {

    private Long id;
    private String skucode;
    private BigDecimal price;
    private Integer quantity;
}
