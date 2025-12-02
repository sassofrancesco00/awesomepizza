package com.awesomepizza.order_management.model.entity;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Francesco Sasso
 * @version 1.0
 * @since 2025
 */

@Embeddable
@Data
public class OrderItem {

    @NotBlank
    private String pizzaType;

    @Min(1)
    private Integer quantity;

    private String notes;

    public OrderItem() {
    }

    public OrderItem(String pizzaType, Integer quantity, String notes) {
        this.pizzaType = pizzaType;
        this.quantity = quantity;
        this.notes = notes;
    }
}
