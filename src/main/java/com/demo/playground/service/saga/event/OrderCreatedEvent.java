package com.demo.playground.service.saga.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderCreatedEvent {
    private final Long orderId;
    private final Long customerId;
    private final java.math.BigDecimal amount;
}
