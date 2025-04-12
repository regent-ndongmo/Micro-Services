package com.microservice.order.openFeignConfig;

import com.microservice.order.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PRODUCT")
public interface ProductFeignProduct {
    @GetMapping("/product/products/{id}")
    Product getProduitById(@PathVariable("id") Long id);
}

