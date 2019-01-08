package com.alpha.payment;

public interface PaymentMethod {
    int pay(int cents);
    int getBalance();
    void replenishBalance(int sum);
}