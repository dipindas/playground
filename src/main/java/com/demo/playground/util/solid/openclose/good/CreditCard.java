package com.demo.playground.util.solid.openclose.good;

public class CreditCard implements PaymentDetails {

    private int cardNumber;

    public CreditCard(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay() {
        System.out.println("Payment through Credit card");
    }
}
