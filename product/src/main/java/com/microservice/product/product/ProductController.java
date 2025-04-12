package com.microservice.product.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
     private final ProductService productService;
    @GetMapping
    public ResponseEntity<PagedResponse<ProductResponse>> getAllProducts(
        @RequestParam(name = "page", required = false, defaultValue = AppConstants.PAGE_NUMBER) int page,
        @RequestParam(name = "size", required = false, defaultValue = AppConstants.PAGE_SIZE) int size
    ){
        return ResponseEntity.ok(productService.getAllProducts(page, size));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct (@RequestBody ProductRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct (@PathVariable long id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }
}
