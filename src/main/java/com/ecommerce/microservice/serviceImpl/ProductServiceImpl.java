package com.ecommerce.microservice.serviceImpl;

import com.ecommerce.microservice.dto.ProductRequest;
import com.ecommerce.microservice.entities.Product;

import java.util.List;

public interface ProductServiceImpl {
    Product create(ProductRequest productRequest);
    Product findById(Long id);
    Product update(ProductRequest productRequest,Long id);
    void deActivate(Long id);
    List<Product> getAllProducts();
}
