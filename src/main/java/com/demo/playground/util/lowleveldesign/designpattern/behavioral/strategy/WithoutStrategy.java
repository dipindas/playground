package com.demo.playground.util.lowleveldesign.designpattern.behavioral.strategy;

public class WithoutStrategy {
    public static void main(String[] args) {
        PaymentService2 paymentService = new PaymentService2();
        paymentService.processPayment("debit");
        paymentService.processPayment("credit");
    }
}

class PaymentService2 {
    public void processPayment(String payment) {
        if(payment.equalsIgnoreCase("debit")) {
            System.out.println("Debit card payment");
        } else if(payment.equalsIgnoreCase("credit")) {
            System.out.println("Credit card payment");
        }
    }
}
