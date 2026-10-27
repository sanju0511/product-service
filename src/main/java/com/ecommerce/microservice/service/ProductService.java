package com.ecommerce.microservice.service;

import com.ecommerce.microservice.dto.ProductRequest;
import com.ecommerce.microservice.entities.Product;
import com.ecommerce.microservice.exception.ProductNotFoundException;
import com.ecommerce.microservice.repo.ProductRepo;
import com.ecommerce.microservice.serviceImpl.ProductServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService implements ProductServiceImpl {

   private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;

    }

    @Override
    public Product create(ProductRequest productRequest) {
        Product product=new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        productRepo.save(product);
        return product;
    }

    @Override
    public Product findById(Long id){
        return productRepo.findById(id).filter(Product::getActive)
                .orElseThrow(()->new ProductNotFoundException("Product not Found"+id));

    }

    @Override
    public Product update(ProductRequest productRequest, Long id) {
        Product product=findById(id);
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());

        productRepo.save(product);
    return product;
    }

    @Override
    @Transactional
    public void deActivate(Long id) {
            Product product=findById(id);
            product.deActivate();
    }

    @Override
    public List<Product> getAllProducts() {

        return productRepo.findByActiveTrue();
    }
}
