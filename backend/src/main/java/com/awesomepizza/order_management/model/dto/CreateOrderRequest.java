package com.awesomepizza.order_management.model.dto;

import com.awesomepizza.order_management.model.entity.OrderItem;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * @author Francesco Sasso
 * @version 1.0
 * @since 2025
 */

@Data
public class CreateOrderRequest {

    @NotEmpty
    private String customerName;

    private String address;

    @NotNull
    @Valid
    private List<OrderItem> items;
}
