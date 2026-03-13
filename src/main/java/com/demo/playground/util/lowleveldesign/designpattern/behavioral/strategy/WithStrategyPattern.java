package com.demo.playground.util.lowleveldesign.designpattern.behavioral.strategy;

public class WithStrategyPattern {
    public static void main(String[] args) {
        /**
         * A strategy is defined. A strategy is nothing but an abstraction
         * The invoker has to set the strategy first and then call its corresponding methods.
         */
        PaymentService paymentService = new PaymentService();
        paymentService.setPaymentStrategy(new DebitCard());
        paymentService.pay();

        paymentService.setPaymentStrategy(new CreditCard());
        paymentService.pay();
    }
}

interface PaymentStrategy {
    void pay();
}

class DebitCard implements PaymentStrategy {

    @Override
    public void pay() {
        System.out.println("Payment through debit card");
    }
}

class CreditCard implements PaymentStrategy {

    @Override
    public void pay() {
        System.out.println("Payment through credit card");
    }
}

class PaymentService {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void pay() {
        paymentStrategy.pay();
    }
}