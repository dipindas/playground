package com.demo.playground.util.solid.openclose.good;

public class DebitCard implements PaymentDetails {

    private int cardNumber;

    public DebitCard(int cardNumber) {
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
        System.out.println("Payment through Debit card");
    }
}
