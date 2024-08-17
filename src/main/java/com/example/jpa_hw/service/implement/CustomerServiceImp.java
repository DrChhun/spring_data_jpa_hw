package com.example.jpa_hw.service.implement;

import com.example.jpa_hw.models.Customer;
import com.example.jpa_hw.models.Email;
import com.example.jpa_hw.models.dto.request.CustomerRequest;
import com.example.jpa_hw.models.dto.response.customer.CustomerDTO;
import com.example.jpa_hw.models.dto.response.customer.CustomerResponseDTO;
import com.example.jpa_hw.models.dto.response.email.EmailResponseDTO;
import com.example.jpa_hw.models.dto.response.order.OrderDTO;
import com.example.jpa_hw.repository.CustomerRepository;
import com.example.jpa_hw.repository.EmailRepository;
import com.example.jpa_hw.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImp implements CustomerService {
    private final CustomerRepository customerRepository;
    private final EmailRepository emailRepository;
    private final CustomerRequest customerRequest;

    @Override
    public CustomerResponseDTO createCustomer(CustomerRequest data) {
//        return customerRepository.save(customerRequest.toEntity(data)).toDto();
        //toEntity
        Email newEmail = Email.builder()
                .id(null)
                .email(data.getEmail())
                .build();
        Email emailResult = emailRepository.save(newEmail);

        Customer newCustomer = Customer.builder()
                .customerId(null)
                .customerName(data.getCustomerName())
                .address(data.getAddress())
                .phoneNumber(data.getPhoneNumber())
                .email(newEmail)
                .build();
        Customer customerResult = customerRepository.save(newCustomer);

        EmailResponseDTO showEmail = EmailResponseDTO.builder()
                .id(emailResult.getId())
                .email(emailResult.getEmail())
                .build();
        return CustomerResponseDTO.builder()
                .customerId(customerResult.getCustomerId())
                .customerName(data.getCustomerName())
                .address(data.getAddress())
                .phoneNumber(data.getPhoneNumber())
                .email(showEmail)
                .build();
    }

    @Override
    public List<Customer> getAllCustomers(int pageNumber, int pageSize, String sortOrder) {
        Page<Customer> page = customerRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.fromString(sortOrder), "customerId")));
        return page.getContent();
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElse(null);
    }

    @Override
    public CustomerResponseDTO updateCustomer(Long id, CustomerRequest data) {
        Optional<Customer> checkCustomer = customerRepository.findById(id);
        Optional<Email> checkEmail = emailRepository.findById(checkCustomer.get().getEmail().getId());

        Email newEmail = checkEmail.get().builder()
                .id(checkEmail.get().getId())
                .email(data.getEmail())
                .build();
        Email updateEmail = emailRepository.save(newEmail);

        Customer newCustomer = checkCustomer.get().builder()
                .customerId(checkCustomer.get().getCustomerId())
                .customerName(data.getCustomerName())
                .address(data.getAddress())
                .phoneNumber(data.getPhoneNumber())
                .email(newEmail)
                .build();
        Customer customerResult = customerRepository.save(newCustomer);

        EmailResponseDTO showEmail = EmailResponseDTO.builder()
                .id(updateEmail.getId())
                .email(updateEmail.getEmail())
                .build();
        return CustomerResponseDTO.builder()
                .customerId(customerResult.getCustomerId())
                .customerName(data.getCustomerName())
                .address(data.getAddress())
                .phoneNumber(data.getPhoneNumber())
                .email(showEmail)
                .build();
    }
}
