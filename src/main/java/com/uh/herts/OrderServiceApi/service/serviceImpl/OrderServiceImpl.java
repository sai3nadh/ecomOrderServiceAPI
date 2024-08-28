package com.uh.herts.OrderServiceApi.service.serviceImpl;


import com.uh.herts.OrderServiceApi.entity.Order;
import com.uh.herts.OrderServiceApi.entity.OrderItem;
import com.uh.herts.OrderServiceApi.entity.Product;
import com.uh.herts.OrderServiceApi.exception.OrderAPIException;
import com.uh.herts.OrderServiceApi.payload.OrderDTO;
import com.uh.herts.OrderServiceApi.payload.OrderItemDTO;
import com.uh.herts.OrderServiceApi.repository.OrderItemRepository;
import com.uh.herts.OrderServiceApi.repository.OrderRepository;
import com.uh.herts.OrderServiceApi.repository.ProductRepository;
import com.uh.herts.OrderServiceApi.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    @Override
    public Order createOrder(Order order) {
        // Assuming order and order items are already properly populated
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long id) {
//        Optional<Order> order = orderRepository.findById(id);
//        if (order.isPresent()) {
//            return order.get();
//        } else {
//            throw new RuntimeException("Order not found");
//        }
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderAPIException(HttpStatus.NOT_FOUND, "Order not found with ID: " + id));

    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        return orderRepository.findByUserId(userId);
    }


    @Transactional
    @Override
    public Order createOrder(OrderDTO orderDTO) {
        // Create the Order entity from the DTO
        Order order = new Order();
        order.setUserId(orderDTO.getUserId());
        order.setCustomerName(orderDTO.getCustomerName());
        order.setCustomerEmail(orderDTO.getCustomerEmail());
        order.setTotalAmount(orderDTO.getTotalAmount());
        order.setStatus("Pending");  // Default status

        // Save the order to generate the orderId
        orderRepository.save(order);

        for (OrderItemDTO itemDTO : orderDTO.getOrderItems()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);  // Setting the Product entity
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setPrice(itemDTO.getPrice());

            orderItemRepository.save(orderItem);
        }


        // Process each OrderItemDTO and save the OrderItem entity
        /*for (OrderItemDTO itemDTO : orderDTO.getOrderItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProductId(itemDTO.getProductId());
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setPrice(itemDTO.getPrice());

            // Save each order item
            orderItemRepository.save(orderItem);
        }*/

        return order;
    }
    @Transactional
    @Override
    public void deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            throw new RuntimeException("Order not found");
        }
    }
}
/*

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Transactional
    public Order createOrder(Order order) {
        // Calculate total amount
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (OrderItem item : order.getOrderItems()) {
            Product product = productRepository.findById(item.getProduct().getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            item.setPrice(product.getPrice());
            totalAmount = totalAmount.add(product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }
        order.setTotalAmount(totalAmount);
        return orderRepository.save(order);
    }

    // Other methods like update, cancel, get order by ID, etc.
}

*/
