package com.demo.playground.service.saga.orchestration;

import com.demo.playground.entity.saga.SagaOrder;
import com.demo.playground.repository.saga.SagaOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrchestrationOrderService {

    private final SagaOrderRepository orderRepository;

    @Transactional
    public SagaOrder createOrder(Long customerId, BigDecimal amount) {
        log.info("Orchestration: Creating order for customer {} with amount {}", customerId, amount);
        SagaOrder order = SagaOrder.builder()
                .customerId(customerId)
                .amount(amount)
                .status("PENDING")
                .build();
        return orderRepository.save(order);
    }

    @Transactional
    public void approveOrder(Long orderId) {
        log.info("Orchestration: Approving order {}", orderId);
        orderRepository.findById(orderId).ifPresent(order -> {
            order.setStatus("APPROVED");
            orderRepository.save(order);
        });
    }

    @Transactional
    public void rejectOrder(Long orderId) {
        log.info("Orchestration: Rejecting order {}", orderId);
        orderRepository.findById(orderId).ifPresent(order -> {
            order.setStatus("REJECTED");
            orderRepository.save(order);
        });
    }
}
