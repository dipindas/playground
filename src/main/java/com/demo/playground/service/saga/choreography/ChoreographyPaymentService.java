package com.demo.playground.service.saga.choreography;

import com.demo.playground.entity.saga.SagaPayment;
import com.demo.playground.repository.saga.SagaPaymentRepository;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class ChoreographyPaymentService {

    private final SagaPaymentRepository paymentRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleOrderCreatedEvent(OrderCreatedEvent event) {
        log.info("Choreography: Received OrderCreatedEvent for order {}. Processing payment.", event.getOrderId());

        // Simulate payment failure for amounts >= 1000
        boolean isSuccess = event.getAmount().doubleValue() < 1000.0;

        SagaPayment payment = SagaPayment.builder()
                .orderId(event.getOrderId())
                .amount(event.getAmount())
                .status(isSuccess ? "SUCCESS" : "FAILED")
                .build();

        paymentRepository.save(payment);

        log.info("Choreography: Payment for order {} processed (success={}). Publishing PaymentProcessedEvent.", event.getOrderId(), isSuccess);
        eventPublisher.publishEvent(new PaymentProcessedEvent(event.getOrderId(), isSuccess));
    }
}
