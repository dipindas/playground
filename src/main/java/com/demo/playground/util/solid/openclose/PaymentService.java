package com.demo.playground.util.solid.openclose;

public class PaymentService {
    public void makePayment(String paymentMethod) {
        if(paymentMethod.equalsIgnoreCase("credit")) {
            System.out.println("Payment using credit cards");
        } else if(paymentMethod.equalsIgnoreCase("debit")) {
            System.out.println("Payment using debit cards");
        }
    }
}
