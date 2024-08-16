package com.example.jpa_hw.service.implement;

import com.example.jpa_hw.models.Product;
import com.example.jpa_hw.models.dto.request.ProductRequest;
import com.example.jpa_hw.models.dto.response.product.ProductResponseDTO;
import com.example.jpa_hw.repository.ProductRepository;
import com.example.jpa_hw.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImp implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public ProductResponseDTO createProduct(ProductRequest productRequest) {
        Product newProduct = Product.builder()
                .productId(null)
                .unitPrice(productRequest.getUnitPrice())
                .productName(productRequest.getProductName())
                .description(productRequest.getDescription())
                .productOrders(null)
                .build();
        Product savedProduct = productRepository.save(newProduct);
        return ProductResponseDTO.builder()
                .id(savedProduct.getProductId())
                .productName(savedProduct.getProductName())
                .unitPrice(savedProduct.getUnitPrice())
                .description(savedProduct.getDescription())
                .build();
    }

    @Override
    public List<ProductResponseDTO> getAllProducts(int pageNumber, int pageSize, String orderDirection) {
        return productRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.fromString(orderDirection), "productId"))).stream().map(product ->ProductResponseDTO.builder()
                        .id(product.getProductId())
                        .productName(product.getProductName())
                        .description(product.getDescription())
                        .unitPrice(product.getUnitPrice())
                        .build())
                        .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {
        return productRepository.findById(id).map(product ->ProductResponseDTO.builder()
                .id(product.getProductId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .unitPrice(product.getUnitPrice())
                .build())
                .orElse(null);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductResponseDTO updateProductById(Long id, ProductRequest productRequest) {
        Optional<Product> getProduct = productRepository.findById(id);
        Product updateProduct = getProduct.get().builder()
                .productId(id)
                .productName(productRequest.getProductName())
                .description(productRequest.getDescription())
                .unitPrice(productRequest.getUnitPrice())
                .build();
        Product saved = productRepository.save(updateProduct);

        return ProductResponseDTO.builder()
                .id(saved.getProductId())
                .productName(saved.getProductName())
                .description(saved.getDescription())
                .unitPrice(saved.getUnitPrice())
                .build();
    }
}
