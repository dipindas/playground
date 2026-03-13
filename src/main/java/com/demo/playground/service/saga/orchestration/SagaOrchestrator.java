package com.demo.playground.service.saga.orchestration;

import com.demo.playground.entity.saga.SagaOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class SagaOrchestrator {

    private final OrchestrationOrderService orderService;
    private final OrchestrationPaymentService paymentService;

    public SagaOrder executeSaga(Long customerId, BigDecimal amount) {
        log.info("Saga Orchestrator: Starting saga for customer {} with amount {}", customerId, amount);

        // Step 1: Create Order
        SagaOrder order = orderService.createOrder(customerId, amount);

        try {
            // Step 2: Process Payment
            boolean paymentSuccess = paymentService.processPayment(order.getId(), amount);

            if (paymentSuccess) {
                // Step 3a: Approve Order
                orderService.approveOrder(order.getId());
                order.setStatus("APPROVED");
                log.info("Saga Orchestrator: Saga completed successfully. Order {} approved.", order.getId());
            } else {
                // Step 3b: Reject Order (Compensating action is not needed for Order, just state change)
                orderService.rejectOrder(order.getId());
                order.setStatus("REJECTED");
                log.info("Saga Orchestrator: Saga failed at payment step. Order {} rejected.", order.getId());
            }
        } catch (Exception e) {
            log.error("Saga Orchestrator: Unexpected error occurred during saga execution.", e);
            // Compensating actions
            paymentService.refundPayment(order.getId());
            orderService.rejectOrder(order.getId());
            order.setStatus("REJECTED");
            log.info("Saga Orchestrator: Compensating transactions executed. Order {} rejected.", order.getId());
        }

        return order;
    }
}
