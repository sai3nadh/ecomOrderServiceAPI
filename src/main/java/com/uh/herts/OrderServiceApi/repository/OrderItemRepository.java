package com.uh.herts.OrderServiceApi.repository;

import com.uh.herts.OrderServiceApi.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {}


