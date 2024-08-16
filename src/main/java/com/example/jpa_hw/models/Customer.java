package com.example.jpa_hw.models;

import com.example.jpa_hw.models.dto.response.customer.CustomerResponseDTO;
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

//    public CustomerResponseDTO toDto() {
//        return CustomerResponseDTO.builder()
//                .customerId(this.customerId)
//                .email(this.email)
//                .customerName(this.customerName)
//                .address(this.address)
//                .phoneNumber(this.phoneNumber)
//                .build();
//    }
}
