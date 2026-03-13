package com.demo.playground.repository.saga;

import com.demo.playground.entity.saga.SagaOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SagaOrderRepository extends JpaRepository<SagaOrder, Long> {
}
