package com.example.jpa_hw.models.dto.request;

import com.example.jpa_hw.models.Customer;
import com.example.jpa_hw.models.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Service
public class CustomerRequest {
    private String customerName;
    private String address;
    private String phoneNumber;
    private String email;

    public Customer toEntity(CustomerRequest data) {
        Email newEmail = Email.builder()
                .email(data.getEmail())
                .build();

        Customer newCustomer = Customer.builder()
                .customerId(null)
                .email(newEmail)
                .phoneNumber(data.getPhoneNumber())
                .address(data.getAddress())
                .build();

        return newCustomer;
    }
}
