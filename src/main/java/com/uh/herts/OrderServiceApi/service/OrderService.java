package com.uh.herts.OrderServiceApi.service;

import com.uh.herts.OrderServiceApi.entity.Order;
import com.uh.herts.OrderServiceApi.payload.OrderDTO;

import java.util.List;

public interface OrderService {

    Order createOrder(Order order);

    Order getOrderById(Long id);

    List<Order> getAllOrders();

    Order createOrder(OrderDTO orderDTO);

    void deleteOrder(Long id);

    List<Order> getOrdersByUserId(int userId);

}
