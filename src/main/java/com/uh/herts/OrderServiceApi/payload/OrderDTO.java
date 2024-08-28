package com.uh.herts.OrderServiceApi.payload;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDTO {
    private int userId;
    private String customerName;
    private String customerEmail;
    private BigDecimal totalAmount;
    private List<OrderItemDTO> orderItems;
}