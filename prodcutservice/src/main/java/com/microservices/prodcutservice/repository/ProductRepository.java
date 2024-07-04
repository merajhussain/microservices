package com.microservices.prodcutservice.repository;

import com.microservices.prodcutservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,String> {
}
