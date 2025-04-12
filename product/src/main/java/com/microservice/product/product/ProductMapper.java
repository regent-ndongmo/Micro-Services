package com.microservice.product.product;


public class ProductMapper {
    public static ProductResponse mapToDto (Product entity) {
        if (entity == null) throw new NullPointerException("entity can not be null");
        ProductResponse dto = ProductResponse.builder()
        .id(entity.getId())
        .name(entity.getName())
        .description(entity.getDescription())
        .price(entity.getPrice())
        .quantity(entity.getQuantity())
        .build();
        return dto;
    }
    
    public static Product mapToEntity (ProductRequest dto) {
        Product entity = Product.builder()
        .name(dto.getName())
        .description(dto.getDescription())
        .price(dto.getPrice())
        .quantity(dto.getQuantity())
        .build(); 
        return entity;
    }
}
