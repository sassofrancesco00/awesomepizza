package com.awesomepizza.order_management.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Francesco Sasso
 * @version 1.0
 * @since 2025
 */
@Entity(name = "pizza_order")
@Table(indexes = {
        @Index(name = "idx_order_code", columnList = "orderCode"),
        @Index(name = "idx_status_created", columnList = "status, createdAt")
})
@Data
public class Order {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true, length = 40)
    private String orderCode;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderItem> items = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime takenInChargeAt;

    private LocalDateTime completedAt;

    @Version
    private Long version;

    @PrePersist
    public void prePersist() {
        if (id == null) id = UUID.randomUUID();
        if (createdAt == null) createdAt = LocalDateTime.now();
        if (status == null) status = OrderStatus.PENDING;
        if (orderCode == null) orderCode = OrderCodeGenerator.generate(createdAt);
    }
}
