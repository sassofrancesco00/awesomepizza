package com.awesomepizza.order_management.controller;

import com.awesomepizza.order_management.model.dto.CreateOrderRequest;
import com.awesomepizza.order_management.model.dto.OrderResponse;
import com.awesomepizza.order_management.model.entity.Order;
import com.awesomepizza.order_management.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Francesco Sasso
 * @version 1.0
 * @since 2025
 */
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody CreateOrderRequest req) {
        Order o = service.createOrder(req);
        OrderResponse resp = toResponse(o);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{orderCode}/status")
    public ResponseEntity<OrderResponse> getStatus(@PathVariable String orderCode) {
        Order o = service.getByOrderCode(orderCode);
        return ResponseEntity.ok(toResponse(o));
    }

    private OrderResponse toResponse(Order o) {
        OrderResponse r = new OrderResponse();
        r.setOrderCode(o.getOrderCode());
        r.setStatus(o.getStatus());
        r.setItems(o.getItems());
        r.setCreatedAt(o.getCreatedAt());
        r.setTakenInChargeAt(o.getTakenInChargeAt());
        r.setCompletedAt(o.getCompletedAt());
        return r;
    }
}
