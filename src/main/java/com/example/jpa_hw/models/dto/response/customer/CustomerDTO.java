package com.example.jpa_hw.models.dto.response.customer;

import com.example.jpa_hw.models.dto.response.email.EmailResponseDTO;
import com.example.jpa_hw.models.dto.response.order.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CustomerDTO {
    private Long customerId;
    private String customerName;
    private String address;
    private String phoneNumber;
    private EmailResponseDTO email;
    private OrderDTO orderList;
}
