package com.example.jpa_hw.repository;

import com.example.jpa_hw.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
