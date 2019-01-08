package com.alpha.payment;

public class DebitCard extends Card {
    public DebitCard(int balance,String nameOnCard, String number, String cvv, String expirationDate) {
        super(balance,nameOnCard, number, cvv, expirationDate);
    }
    @Override
    public int pay(int cents) {
        if(this.balance - cents < 0){
            throw new RuntimeException("There is not enough money on the balance");
        }
        this.balance -=cents;
        return cents;
    }
    @Override
    protected String getType() {
        return "debit";
    }

}