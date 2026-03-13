# Saga Design Pattern Guide

## Overview

The Saga design pattern is a way to manage data consistency across microservices in distributed transaction scenarios. Since microservices typically use their own separate databases, a traditional ACID transaction (like two-phase commit) is not viable. A Saga is a sequence of local transactions. Each local transaction updates the database and publishes a message or event to trigger the next local transaction in the saga.

If a local transaction fails because it violates a business rule, the saga executes a series of **compensating transactions** that undo the changes that were made by the preceding local transactions.

There are two common ways to implement a Saga: **Choreography** and **Orchestration**.

---

## 1. Saga Choreography

In Choreography, there is no central coordinator. Each service participating in the saga works independently, listens to events from other services, performs its local transaction, and publishes events.

### How it works (Simulation in this application):
1. The **ChoreographyOrderService** receives a request to create an order.
2. It saves the order with a `PENDING` status.
3. It publishes an `OrderCreatedEvent`.
4. The **ChoreographyPaymentService** listens for the `OrderCreatedEvent`.
5. It processes the payment (simulated logic: fails if amount >= 1000).
6. It publishes a `PaymentProcessedEvent` containing the result (success or failure).
7. The **ChoreographyOrderService** listens for the `PaymentProcessedEvent`.
8. Depending on the success flag, it updates the order status to either `APPROVED` or `REJECTED`.

### Pros:
- No centralized single point of failure or bottleneck.
- Well-suited for simple sagas with few participants.

### Cons:
- Difficult to track the current state of a saga since logic is distributed.
- Harder to debug and maintain as the complexity grows (can lead to cyclic dependencies).

---

## 2. Saga Orchestration

In Orchestration, a central coordinator (the Orchestrator) tells the participating services what local transactions to execute. The orchestrator is responsible for the overall workflow, handling failures, and invoking compensating actions.

### How it works (Simulation in this application):
1. The **SagaOrchestrator** receives the initial request.
2. It explicitly calls the **OrchestrationOrderService** to create a pending order.
3. Next, it calls the **OrchestrationPaymentService** to process the payment synchronously.
4. Based on the returned boolean result from the payment service:
   - If successful, it calls the order service to approve the order.
   - If failed, it calls the order service to reject the order (no compensating action needed for payment since it failed).
5. If an unexpected exception occurs during the flow, the orchestrator initiates compensating transactions (e.g., calling `refundPayment` on the payment service and `rejectOrder` on the order service).

### Pros:
- Centralized logic makes the workflow easy to understand and maintain.
- Simpler to implement compensating transactions and track the state of the saga.
- Better suited for complex workflows involving many services.

### Cons:
- Introduces a central point of failure (the orchestrator).
- The orchestrator might become too complex if it holds too much business logic.

---

## MongoDB Integration

This repository also includes a basic integration with MongoDB using Spring Data MongoDB.

### Configuration
The application is configured to connect to a local MongoDB instance via `application.properties`:
```properties
spring.data.mongodb.uri=mongodb://localhost:27017/playground_db
```

### Features
- A `Book` document entity mapped to the `books` collection.
- A `BookRepository` extending `MongoRepository` for built-in CRUD operations and custom query methods.
- A `BookService` containing the business logic.
- A `BookController` exposing RESTful endpoints under `/api/mongo/books` for basic CRUD operations (Create, Read, Update, Delete).
