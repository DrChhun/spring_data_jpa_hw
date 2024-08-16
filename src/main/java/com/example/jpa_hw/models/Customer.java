package com.example.jpa_hw.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    private String customerName;
    private String address;
    private String phoneNumber;
    @OneToOne
    @JoinColumn(name = "email_id")
    private Email email;
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
}
