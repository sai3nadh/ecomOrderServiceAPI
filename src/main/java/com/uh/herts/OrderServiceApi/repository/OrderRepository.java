package com.uh.herts.OrderServiceApi.repository;


import com.uh.herts.OrderServiceApi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserId(int userId);  // Query method to find orders by userId

}
