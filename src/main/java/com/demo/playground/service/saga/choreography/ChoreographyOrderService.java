package com.demo.playground.service.saga.choreography;

import com.demo.playground.entity.saga.SagaOrder;
import com.demo.playground.repository.saga.SagaOrderRepository;
import com.demo.playground.service.saga.event.OrderCreatedEvent;
import com.demo.playground.service.saga.event.PaymentProcessedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChoreographyOrderService {

    private final SagaOrderRepository orderRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public SagaOrder createOrder(Long customerId, BigDecimal amount) {
        log.info("Choreography: Creating order for customer {} with amount {}", customerId, amount);
        SagaOrder order = SagaOrder.builder()
                .customerId(customerId)
                .amount(amount)
                .status("PENDING")
                .build();

        order = orderRepository.save(order);

        log.info("Choreography: Order {} created. Publishing OrderCreatedEvent.", order.getId());
        eventPublisher.publishEvent(new OrderCreatedEvent(order.getId(), customerId, amount));

        return order;
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handlePaymentProcessedEvent(PaymentProcessedEvent event) {
        log.info("Choreography: Received PaymentProcessedEvent for order {} (success={})", event.getOrderId(), event.isSuccess());

        orderRepository.findById(event.getOrderId()).ifPresent(order -> {
            if (event.isSuccess()) {
                order.setStatus("APPROVED");
                log.info("Choreography: Order {} approved.", order.getId());
            } else {
                order.setStatus("REJECTED");
                log.info("Choreography: Order {} rejected due to payment failure.", order.getId());
            }
            orderRepository.save(order);
        });
    }
}
