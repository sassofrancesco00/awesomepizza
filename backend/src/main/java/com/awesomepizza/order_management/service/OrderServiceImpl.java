package com.awesomepizza.order_management.service;

import com.awesomepizza.order_management.exception.InvalidOrderStateException;
import com.awesomepizza.order_management.exception.OrderNotFoundException;
import com.awesomepizza.order_management.model.dto.CreateOrderRequest;
import com.awesomepizza.order_management.model.entity.Order;
import com.awesomepizza.order_management.model.entity.OrderItem;
import com.awesomepizza.order_management.model.entity.OrderStatus;
import com.awesomepizza.order_management.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Locked;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    @Transactional
    public Order createOrder(CreateOrderRequest request) {
        Order o = new Order();
        o.setItems(request.getItems().stream()
                .map(i -> new OrderItem(i.getPizzaType(), i.getQuantity(), i.getNotes()))
                .collect(Collectors.toList()));
        // optional fields
        o.setCreatedAt(LocalDateTime.now());
        o.setStatus(OrderStatus.PENDING);
        return repo.save(o);
    }

    @Override
    @Transactional()
    public Order getByOrderCode(String orderCode) {
        return repo.findByOrderCode(orderCode)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with code: " + orderCode));
    }

    @Override
    @Transactional()
    public java.util.List<Order> getQueue() {
        return repo.findByStatusOrderByCreatedAtAsc(OrderStatus.PENDING);
    }

    @Override
    @Transactional
    public Order takeOrder(UUID id) {
        Order o = repo.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found: " + id));
        if (o.getStatus() != OrderStatus.PENDING) {
            throw new InvalidOrderStateException("Only PENDING orders may be taken. Current: " + o.getStatus());
        }
        o.setStatus(OrderStatus.IN_PROGRESS);
        o.setTakenInChargeAt(LocalDateTime.now());
        return repo.save(o);
    }


    @Override
    @Transactional
    public Order completeOrder(UUID id) {
        Order o = repo.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found: " + id));
        if (o.getStatus() != OrderStatus.IN_PROGRESS) {
            throw new InvalidOrderStateException("Only IN_PROGRESS orders may be completed. Current: " + o.getStatus());
        }
        o.setStatus(OrderStatus.COMPLETED);
        o.setCompletedAt(LocalDateTime.now());
        return repo.save(o);
    }
}
