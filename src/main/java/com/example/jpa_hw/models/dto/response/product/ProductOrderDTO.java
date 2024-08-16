package com.example.jpa_hw.models.dto.response.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductOrderDTO {
    private Long productId;
    private String productName;
    private Float unitPrice;
    private String description;
}
