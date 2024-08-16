package com.example.jpa_hw.models.mapper;

import com.example.jpa_hw.models.Customer;
import com.example.jpa_hw.models.Email;
import com.example.jpa_hw.models.dto.request.CustomerRequest;
import com.example.jpa_hw.models.dto.response.customer.CustomerResponseDTO;
import com.example.jpa_hw.repository.CustomerRepository;
import com.example.jpa_hw.repository.EmailRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomerMapper {
    public Customer toEntity(CustomerRequest customerRequest) {
        Email newEmail = Email.builder()
                .id(null)
                .email(customerRequest.getEmail())
                .build();

        return Customer.builder()
                .customerId(null)
                .customerName(customerRequest.getCustomerName())
                .address(customerRequest.getAddress())
                .phoneNumber(customerRequest.getPhoneNumber())
                .email(newEmail)
                .build();
    }

    public CustomerResponseDTO toDto(Customer customer) {
        return CustomerResponseDTO.builder()
                .customerId(customer.getCustomerId())
                .address(customer.getAddress())
                .phoneNumber(customer.getPhoneNumber())
                .email(null)
                .build();
    }
}
