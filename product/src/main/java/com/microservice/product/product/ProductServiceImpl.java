package com.microservice.product.product;

import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class ProductServiceImpl implements ProductService {
     private final ProductRepository productRepository;
     
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public PagedResponse<ProductResponse> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findAll(pageable);
        List<Product> products = productPage.getContent().isEmpty() ? Collections.emptyList() : productPage.getContent();
        return PagedResponse.<ProductResponse>builder()
        .last(productPage.isLast())
        .first(productPage.isFirst())
        .content(products.stream().map(ProductMapper::mapToDto).toList())
        .totalPages(productPage.getTotalPages())
        .build();
        // return products.map(pr -> ProductMapper.mapToDto(pr));
    }

    @Override
    public ProductResponse createProduct(ProductRequest dto) {
        Product product = ProductMapper.mapToEntity(dto); 
        Product savedProduct = productRepository.save(product); 
        return ProductMapper.mapToDto(savedProduct); 
    }
    

    @Override
    public ProductResponse updateProduct(long id, ProductRequest dto) {
       Product existingProduct = productRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(AppConstants.PRODUCT, AppConstants.ID, id));
        existingProduct.setName(dto.getName());
        existingProduct.setDescription(dto.getDescription());
        existingProduct.setPrice(dto.getPrice());
        existingProduct.setQuantity(dto.getQuantity());
        Product savedProduct = productRepository.save(existingProduct);
        return ProductMapper.mapToDto(savedProduct);
    }

    @Override
    public ProductResponse getProduct(long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(AppConstants.PRODUCT, AppConstants.ID, id));
        return ProductMapper.mapToDto(product);
    }

    @Override
    public void deleteProduct(long id) {
       Product product = productRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(AppConstants.PRODUCT, AppConstants.ID, id));
        productRepository.delete(product);
    }  
}
