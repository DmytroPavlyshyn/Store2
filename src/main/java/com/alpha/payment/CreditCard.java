package com.alpha.payment;

public class CreditCard extends Card {
    public CreditCard(int balance,String nameOnCard, String number, String cvv, String expirationDate) {
        super(balance,nameOnCard, number, cvv, expirationDate);
    }
    @Override
    protected String getType() {
        return "credit";
    }

    @Override
    public int pay(int cents) {
        this.balance -=cents;
        return cents;
    }
}
