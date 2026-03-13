package com.demo.playground.repository.saga;

import com.demo.playground.entity.saga.SagaPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SagaPaymentRepository extends JpaRepository<SagaPayment, Long> {
    List<SagaPayment> findByOrderIdAndStatus(Long orderId, String status);
}
