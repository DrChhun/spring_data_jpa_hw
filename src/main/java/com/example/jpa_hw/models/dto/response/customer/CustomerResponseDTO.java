package com.example.jpa_hw.models.dto.response.customer;

import com.example.jpa_hw.models.Email;
import com.example.jpa_hw.models.dto.response.email.EmailResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponseDTO {
    private Long customerId;
    private String customerName;
    private String address;
    private String phoneNumber;
    private EmailResponseDTO email;
}
