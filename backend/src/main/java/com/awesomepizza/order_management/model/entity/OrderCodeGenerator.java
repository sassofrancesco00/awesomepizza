package com.awesomepizza.order_management.model.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Francesco Sasso
 * @version 1.0
 * @since 2025
 */
public final class OrderCodeGenerator {

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final AtomicInteger COUNTER = new AtomicInteger(0);

    public static String generate(LocalDateTime when) {
        String date = when != null ? when.format(DATE_FMT) : LocalDateTime.now().format(DATE_FMT);
        int seq = COUNTER.incrementAndGet() % 100000;
        return String.format("AWP-%s-%05d", date, seq);
    }
}
