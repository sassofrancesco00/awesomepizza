package com.awesomepizza.order_management.service;

import com.awesomepizza.order_management.model.dto.CreateOrderRequest;
import com.awesomepizza.order_management.model.entity.Order;

import java.util.List;
import java.util.UUID;

/**
 * @author Francesco Sasso
 * @version 1.0
 * @since 2025
 */
public interface OrderService {
    Order createOrder(CreateOrderRequest request);

    Order getByOrderCode(String orderCode);

    List<Order> getQueue();

    Order takeOrder(UUID id);

    Order completeOrder(UUID id);
}
