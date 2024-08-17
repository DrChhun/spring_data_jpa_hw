package com.example.jpa_hw.repository;

import com.example.jpa_hw.models.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
}
