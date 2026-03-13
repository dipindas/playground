package com.demo.playground.service.saga.orchestration;

import com.demo.playground.entity.saga.SagaPayment;
import com.demo.playground.repository.saga.SagaPaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrchestrationPaymentService {

    private final SagaPaymentRepository paymentRepository;

    @Transactional
    public boolean processPayment(Long orderId, BigDecimal amount) {
        log.info("Orchestration: Processing payment for order {} with amount {}", orderId, amount);

        // Simulate payment failure for amounts >= 1000
        boolean isSuccess = amount.doubleValue() < 1000.0;

        SagaPayment payment = SagaPayment.builder()
                .orderId(orderId)
                .amount(amount)
                .status(isSuccess ? "SUCCESS" : "FAILED")
                .build();

        paymentRepository.save(payment);

        return isSuccess;
    }

    @Transactional
    public void refundPayment(Long orderId) {
        log.info("Orchestration: Refunding payment for order {}", orderId);
        List<SagaPayment> successfulPayments = paymentRepository.findByOrderIdAndStatus(orderId, "SUCCESS");
        successfulPayments.forEach(p -> {
            p.setStatus("REFUNDED");
            paymentRepository.save(p);
        });
    }
}
