package com.example.jpa_hw.models;

import com.example.jpa_hw.models.constant.OrderStatusEnum;
import com.example.jpa_hw.models.dto.response.order.OrderResponseDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity(name = "order_tb")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime orderDate;
    private Float totalAmount;
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum orderStatus;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore
    private Customer customer;
    @OneToMany(mappedBy = "order")
    @JsonIgnore
    private List<ProductOrder> productOrders;
}
