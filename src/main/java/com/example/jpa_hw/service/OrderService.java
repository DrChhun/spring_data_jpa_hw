package com.example.jpa_hw.service;

import com.example.jpa_hw.models.Order;
import com.example.jpa_hw.models.dto.request.OrderRequest;
import com.example.jpa_hw.models.dto.response.order.OrderResponseDTO;

import java.util.List;

public interface OrderService {
    OrderResponseDTO saveOrder(List<OrderRequest> orderRequests, Long customerId);

    OrderResponseDTO getOrderById(Long id);

//    List<Object> getOrderByCustomerId(Long id);
}
