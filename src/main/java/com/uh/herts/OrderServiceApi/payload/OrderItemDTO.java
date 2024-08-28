package com.uh.herts.OrderServiceApi.payload;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDTO {
    private int productId;
    private int quantity;
    private BigDecimal price;
}