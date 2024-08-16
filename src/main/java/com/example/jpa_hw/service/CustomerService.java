package com.example.jpa_hw.service;

import com.example.jpa_hw.models.Customer;
import com.example.jpa_hw.models.dto.request.CustomerRequest;
import com.example.jpa_hw.models.dto.response.customer.CustomerResponseDTO;

import java.util.List;

public interface CustomerService {
    CustomerResponseDTO createCustomer(CustomerRequest data);

    List<Customer> getAllCustomers(int pageNumber, int pageSize);

    void deleteCustomer(Long id);

    Customer getCustomerById(Long id);

    CustomerResponseDTO updateCustomer(Long id, CustomerRequest customerRequest);
}
