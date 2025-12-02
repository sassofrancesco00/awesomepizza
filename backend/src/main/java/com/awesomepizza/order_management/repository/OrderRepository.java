package com.awesomepizza.order_management.repository;

import com.awesomepizza.order_management.model.entity.Order;
import com.awesomepizza.order_management.model.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Francesco Sasso
 * @version 1.0
 * @since 2025
 */
public interface OrderRepository extends JpaRepository<Order, UUID> {
    Optional<Order> findByOrderCode(String orderCode);

    List<Order> findByStatusOrderByCreatedAtAsc(OrderStatus status);
}
