package com.awesomepizza.order_management.exception;

/**
 * @author Francesco Sasso
 * @version 1.0
 * @since 2025
 */
public class InvalidOrderStateException extends RuntimeException {
    public InvalidOrderStateException(String message) {
        super(message);
    }
}
