package com.microservice.order.models;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String name;
    private String description;
    private Integer quantity;
    private Double price;
}

