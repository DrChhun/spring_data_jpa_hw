package com.example.jpa_hw.repository;

import com.example.jpa_hw.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
