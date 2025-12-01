package com.awesomepizza.order_management.exception;

/**
 * @author Francesco Sasso
 * @version 1.0
 * @since 2025
 */
public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
