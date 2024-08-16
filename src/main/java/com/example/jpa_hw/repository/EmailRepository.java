package com.example.jpa_hw.repository;

import com.example.jpa_hw.models.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
}
