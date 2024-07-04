package com.microservices.prodcutservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value="product")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class Product {

    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}
