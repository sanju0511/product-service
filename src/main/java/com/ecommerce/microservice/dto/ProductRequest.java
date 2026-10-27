package com.ecommerce.microservice.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class ProductRequest {
    @NotBlank(message = "Product Name is required")
    @Size(max=120)
    String name;

    @Size(max=1000)
    String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01",message = "Price must be greater than zero")
    @Digits(integer = 10,fraction = 2)
    BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
