package com.example.jpa_hw.service;

import com.example.jpa_hw.models.dto.request.ProductRequest;
import com.example.jpa_hw.models.dto.response.product.ProductResponseDTO;

import java.util.Collection;
import java.util.List;

public interface ProductService {
    ProductResponseDTO createProduct(ProductRequest productRequest);

    List<ProductResponseDTO> getAllProducts(int pageNumber, int pageSize, String orderDirection);

    ProductResponseDTO getProductById(Long id);

    void deleteProductById(Long id);

    ProductResponseDTO updateProductById(Long id, ProductRequest productRequest);
}
