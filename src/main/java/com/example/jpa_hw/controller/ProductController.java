package com.example.jpa_hw.controller;

import com.example.jpa_hw.models.constant.OrderDirection;
import com.example.jpa_hw.models.dto.request.ProductRequest;
import com.example.jpa_hw.models.dto.response.GetResponse;
import com.example.jpa_hw.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;
    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody ProductRequest productRequest) {
        return GetResponse.responseCreate(
                "Successfully create new product",
                productService.createProduct(productRequest)
        );
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts(
            @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
            @RequestParam(value = "pageDirection", defaultValue = "ASC")OrderDirection orderDirection
            ) {
        return GetResponse.responseAll(
                "Successfully get all products",
                productService.getAllProducts(pageNumber, pageSize, orderDirection.name()),
                productService.getAllProducts(pageNumber, pageSize, orderDirection.name()).size()
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id) {
        return GetResponse.responseOnce(
          "Successfully get product by id",
          productService.getProductById(id)
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable("id") Long id) {
        productService.deleteProductById(id);
        return GetResponse.responseDelete(
                "Successfully delete product by id"
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateProduct(
            @PathVariable("id") Long id,
            @RequestBody ProductRequest productRequest
    ) {
        return GetResponse.responseOnce(
          "Successfully update product by id",
          productService.updateProductById(id, productRequest)
        );
    };
}
