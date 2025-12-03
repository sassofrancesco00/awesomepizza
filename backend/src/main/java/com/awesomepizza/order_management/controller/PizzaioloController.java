package com.awesomepizza.order_management.controller;

import com.awesomepizza.order_management.model.entity.Order;
import com.awesomepizza.order_management.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author Francesco Sasso
 * @version 1.0
 * @since 2025
 */
@RestController
@RequestMapping("/api/pizzaiolo/orders")
@RequiredArgsConstructor
public class PizzaioloController {

    private final OrderService service;

    @GetMapping("/queue")
    public ResponseEntity<List<Order>> queue() {
        return ResponseEntity.ok(service.getQueue());
    }

    @PutMapping("/{id}/take")
    public ResponseEntity<Order> take(@PathVariable UUID id) {
        return ResponseEntity.ok(service.takeOrder(id));
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<Order> complete(@PathVariable UUID id) {
        return ResponseEntity.ok(service.completeOrder(id));
    }
}
