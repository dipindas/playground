package com.demo.playground.service.saga.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentProcessedEvent {
    private final Long orderId;
    private final boolean success;
}
