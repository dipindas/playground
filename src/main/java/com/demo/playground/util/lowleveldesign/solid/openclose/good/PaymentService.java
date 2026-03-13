package com.demo.playground.util.lowleveldesign.solid.openclose.good;

public class PaymentService {

    public void makePayment(PaymentDetails paymentDetails) {
        paymentDetails.pay();
    }
}
