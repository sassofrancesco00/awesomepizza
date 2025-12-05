package com.awesomepizza.order_management.controller;

import com.awesomepizza.order_management.model.dto.CreateOrderRequest;
import com.awesomepizza.order_management.model.entity.Order;
import com.awesomepizza.order_management.model.entity.OrderItem;
import com.awesomepizza.order_management.model.entity.OrderStatus;
import com.awesomepizza.order_management.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.http.MediaType;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Francesco Sasso
 * @version 1.0
 * @since 2025
 */
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockitoBean
    OrderService service;

    @Test
    void testCreateOrder() throws Exception {
        CreateOrderRequest req = new CreateOrderRequest();
        req.setItems(List.of(new OrderItem("Margherita", 1, "")));

        Order o = new Order();
        o.setOrderCode("ABC123");
        o.setStatus(OrderStatus.PENDING);

        when(service.createOrder(Mockito.any())).thenReturn(o);

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderCode").value("ABC123"));
    }

    @Test
    void testGetStatus() throws Exception {
        Order o = new Order();
        o.setOrderCode("AAA111");
        o.setStatus(OrderStatus.PENDING);

        when(service.getByOrderCode("AAA111")).thenReturn(o);

        mockMvc.perform(get("/api/orders/AAA111/status"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderCode").value("AAA111"));
    }
}

