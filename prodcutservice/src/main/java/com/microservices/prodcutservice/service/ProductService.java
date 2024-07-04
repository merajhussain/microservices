package com.microservices.prodcutservice.service;

import com.microservices.prodcutservice.dto.ProductRequest;
import com.microservices.prodcutservice.dto.ProductResponse;
import com.microservices.prodcutservice.model.Product;
import com.microservices.prodcutservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;



    public void createProduct(final ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product {id} is saved",product.getId());
    }

    public List<ProductResponse> getAllproducts(){

        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();


    }

    private ProductResponse mapToProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();

    }
}
