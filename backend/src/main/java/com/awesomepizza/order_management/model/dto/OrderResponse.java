package com.awesomepizza.order_management.model.dto;

import com.awesomepizza.order_management.model.entity.OrderItem;
import com.awesomepizza.order_management.model.entity.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Francesco Sasso
 * @version 1.0
 * @since 2025
 */

@Data
public class OrderResponse {
    private String orderCode;
    private OrderStatus status;
    private List<OrderItem> items;
    private LocalDateTime createdAt;
    private LocalDateTime takenInChargeAt;
    private LocalDateTime completedAt;
}