package com.microservices.inventoryservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="t_inventory")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long Id;
    private String skucode;
    private Integer quantity;


}
