package com.example.jpa_hw.repository;

import com.example.jpa_hw.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
