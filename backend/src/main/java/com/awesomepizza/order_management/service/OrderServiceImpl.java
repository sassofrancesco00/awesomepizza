package com.awesomepizza.order_management.service;

import com.awesomepizza.order_management.model.dto.CreateOrderRequest;
import com.awesomepizza.order_management.model.entity.Order;
import com.awesomepizza.order_management.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author Francesco Sasso
 * @version 1.0
 * @since 2025
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repo;

    @Override
    public Order createOrder(CreateOrderRequest request) {
        return null;
    }

    @Override
    public Order getByOrderCode(String orderCode) {
        return null;
    }

    @Override
    public List<Order> getQueue() {
        return List.of();
    }

    @Override
    public Order takeOrder(UUID id) {
        return null;
    }

    @Override
    public Order completeOrder(UUID id) {
        return null;
    }
}
