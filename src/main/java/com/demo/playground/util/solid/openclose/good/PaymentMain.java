package com.demo.playground.util.solid.openclose.good;

public class PaymentMain {
    public static void main(String[] args) {
        PaymentService paymentService = new PaymentService();
        paymentService.makePayment(new CreditCard(123456789));
        paymentService.makePayment(new DebitCard(987654321));
    }
}
