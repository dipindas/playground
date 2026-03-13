package com.demo.playground.controller.saga;

import com.demo.playground.entity.saga.SagaOrder;
import com.demo.playground.service.saga.choreography.ChoreographyOrderService;
import com.demo.playground.service.saga.orchestration.SagaOrchestrator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/saga")
@RequiredArgsConstructor
public class SagaController {

    private final ChoreographyOrderService choreographyOrderService;
    private final SagaOrchestrator sagaOrchestrator;

    @PostMapping("/choreography")
    public ResponseEntity<SagaOrder> startChoreographySaga(@RequestParam Long customerId, @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(choreographyOrderService.createOrder(customerId, amount));
    }

    @PostMapping("/orchestration")
    public ResponseEntity<SagaOrder> startOrchestrationSaga(@RequestParam Long customerId, @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(sagaOrchestrator.executeSaga(customerId, amount));
    }
}
