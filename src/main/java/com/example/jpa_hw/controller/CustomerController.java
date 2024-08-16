package com.example.jpa_hw.controller;

import com.example.jpa_hw.models.dto.request.CustomerRequest;
import com.example.jpa_hw.models.dto.response.GetResponse;
import com.example.jpa_hw.repository.CustomerRepository;
import com.example.jpa_hw.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody CustomerRequest data) {
        return GetResponse.responseCreate(
                "Success create new customer",
                customerService.createCustomer(data)
        );
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomers(
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam("pageSize") int pageSize
    ) {
        return GetResponse.responseAll(
                "Successfully get all customers",
                customerService.getAllCustomers(pageNumber, pageSize),
                customerService.getAllCustomers(pageNumber, pageSize).size()
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable("id") Long id) {
        return GetResponse.responseOnce(
                "Successfully get customer by id",
                customerService.getCustomerById(id)
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
        return GetResponse.responseDelete(
                "Successfully delete customer"
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateCustomer(
            @PathVariable("id") Long id,
            @RequestBody CustomerRequest customerRequest
    ) {
        return GetResponse.responseOnce(
                "Successfully update customer",
                customerService.updateCustomer(id, customerRequest)
        );
    }
}
