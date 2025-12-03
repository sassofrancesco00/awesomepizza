package com.awesomepizza.order_management.service;

import com.awesomepizza.order_management.exception.InvalidOrderStateException;
import com.awesomepizza.order_management.exception.OrderNotFoundException;
import com.awesomepizza.order_management.model.dto.CreateOrderRequest;
import com.awesomepizza.order_management.model.entity.Order;
import com.awesomepizza.order_management.model.entity.OrderStatus;
import com.awesomepizza.order_management.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Francesco Sasso
 * @version 1.0
 * @since 2025
 */
public class OrderServiceImplTest {

    private OrderRepository repo;
    private OrderServiceImpl service;

    @BeforeEach
    void setup() {
        repo = Mockito.mock(OrderRepository.class);
        service = new OrderServiceImpl(repo);
    }

    @Test
    void testCreateOrder() {
        CreateOrderRequest req = new CreateOrderRequest();
        req.setItems(List.of(new OrderItemRequest("Margherita", 2, null)));

        Order saved = new Order();
        saved.setStatus(OrderStatus.PENDING);

        when(repo.save(any(Order.class))).thenReturn(saved);

        Order result = service.createOrder(req);

        assertEquals(OrderStatus.PENDING, result.getStatus());
        verify(repo, times(1)).save(any(Order.class));
    }

    @Test
    void testGetByOrderCode_found() {
        Order o = new Order();
        o.setOrderCode("XYZ123");

        when(repo.findByOrderCode("XYZ123")).thenReturn(Optional.of(o));

        Order result = service.getByOrderCode("XYZ123");

        assertEquals("XYZ123", result.getOrderCode());
    }

    @Test
    void testGetByOrderCode_notFound() {
        when(repo.findByOrderCode("000")).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class,
                () -> service.getByOrderCode("000"));
    }

    @Test
    void testTakeOrder_ok() {
        UUID id = UUID.randomUUID();
        Order o = new Order();
        o.setId(id);
        o.setStatus(OrderStatus.PENDING);

        when(repo.findById(id)).thenReturn(Optional.of(o));
        when(repo.save(o)).thenReturn(o);

        Order result = service.takeOrder(id);

        assertEquals(OrderStatus.IN_PROGRESS, result.getStatus());
    }

    @Test
    void testTakeOrder_invalidState() {
        UUID id = UUID.randomUUID();
        Order o = new Order();
        o.setId(id);
        o.setStatus(OrderStatus.COMPLETED);

        when(repo.findById(id)).thenReturn(Optional.of(o));

        assertThrows(InvalidOrderStateException.class, () -> service.takeOrder(id));
    }

    @Test
    void testCompleteOrder_ok() {
        UUID id = UUID.randomUUID();
        Order o = new Order();
        o.setId(id);
        o.setStatus(OrderStatus.IN_PROGRESS);

        when(repo.findById(id)).thenReturn(Optional.of(o));
        when(repo.save(o)).thenReturn(o);

        Order result = service.completeOrder(id);

        assertEquals(OrderStatus.COMPLETED, result.getStatus());
    }

    @Test
    void testCompleteOrder_invalidState() {
        UUID id = UUID.randomUUID();
        Order o = new Order();
        o.setId(id);
        o.setStatus(OrderStatus.PENDING);

        when(repo.findById(id)).thenReturn(Optional.of(o));

        assertThrows(InvalidOrderStateException.class, () -> service.completeOrder(id));
    }
}
