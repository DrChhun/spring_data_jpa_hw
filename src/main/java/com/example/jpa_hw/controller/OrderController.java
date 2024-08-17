package com.example.jpa_hw.controller;

import com.example.jpa_hw.models.constant.OrderStatusEnum;
import com.example.jpa_hw.models.dto.request.OrderRequest;
import com.example.jpa_hw.models.dto.response.GetResponse;
import com.example.jpa_hw.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> addOrder(
            @RequestParam("customerId") Long customerId,
            @RequestBody List<OrderRequest> orderRequests
    ) {
        return GetResponse.responseCreate(
                "Successfully create new order",
                orderService.saveOrder(orderRequests, customerId)
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOrderById(@PathVariable("id") Long id) {
        return GetResponse.responseOnce(
                "Successfullly get order by id",
                orderService.getOrderById(id)
        );
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getOrderByCustomerId(@PathVariable("id") Long id) {
        return GetResponse.responseList(
                "Successfully get order by customer",
                orderService.getOrderByCustomerId(id)
        );
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<?> updateStatusByOrderId(@PathVariable("id") Long id, @RequestParam("status")OrderStatusEnum status) {
        return GetResponse.responseOnce(
          "Successfully update status",
          orderService.updateStatusByOrderId(id, status)
        );
    }
}
