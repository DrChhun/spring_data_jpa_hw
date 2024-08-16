package com.example.jpa_hw.models.dto.response.order;

import com.example.jpa_hw.models.constant.OrderStatusEnum;
import com.example.jpa_hw.models.dto.response.product.ProductOrderDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderResponseDTO {
    private Long orderId;
    private LocalDateTime orderDate;
    private Float totalAmount;
    private OrderStatusEnum orderStatus;
    private List<ProductOrderDTO> productOrders;
}
