package com.alpha.payment;

public class Cash implements PaymentMethod {
    int balance;

    public Cash(int balance) {
        if (balance < 0) {
            throw new RuntimeException("Balance must be positive");
        }
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Cash{" +
                "balance=" + balance +
                '}';
    }

    @Override
    public void replenishBalance(int sum) {
        if (sum < 0) {
            throw new RuntimeException("Sum must be positive");
        }
        this.balance+=sum;
    }

    @Override
    public int pay(int cents) {
        if (this.balance - cents < 0) {
            throw new RuntimeException("There is not enough money on the balance");
        }
        this.balance -= cents;
        return cents;
    }
    @Override
    public int getBalance() {
        return balance;
    }
}