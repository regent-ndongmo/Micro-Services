package com.microservice.product.product;


public interface ProductService {

    PagedResponse<ProductResponse> getAllProducts (int page, int size);

    ProductResponse createProduct (ProductRequest dto);

    ProductResponse updateProduct (long id, ProductRequest dto);

    ProductResponse getProduct (long id);

    void deleteProduct (long id);
}
