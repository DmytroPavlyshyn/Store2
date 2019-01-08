package com.alpha.payment;

import java.text.MessageFormat;

public abstract class Card implements PaymentMethod {
    private final String nameOnCard;
    private final String number;
    private final String cvv;
    private final String expirationDate;
    int balance;
    public Card(int balance, String nameOnCard, String number, String cvv, String expirationDate) {
        if (balance < 0) {
            throw new RuntimeException("Balance must be positive");
        }
        this.nameOnCard = nameOnCard;
        this.number = number;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
    }



    @Override
    public void replenishBalance(int sum) {
        if (sum < 0) {
            throw new RuntimeException("Sum must be positive");
        }
        this.balance+=sum;
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0} card[name = {1}, number = {2}, CVV = {3}, expiration = {4}]", getType(), nameOnCard, number, cvv, expirationDate);
    }

    protected abstract String getType();

}