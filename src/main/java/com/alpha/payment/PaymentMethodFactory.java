package com.alpha.payment;

public class PaymentMethodFactory {
    final static String[] paymentMethods = {"credit", "debit", "cash"};

    public static PaymentMethod getPaymentMethod(String type, int balance, String... cardinfo) {
        switch (type) {
            case "credit":
                return createCreditCard(balance, cardinfo);
            case "debit":
                return createDebitCard(balance);
            case "cash":
                return createCash(balance);
        }
        throw new IllegalArgumentException();
    }

    public static CreditCard createCreditCard(int balance, String... cardinfo) {
        if (cardinfo.length != 3) {
            throw new IllegalArgumentException("There not enough arguments");
        }
        return new CreditCard(balance, cardinfo[0], cardinfo[1], cardinfo[2], cardinfo[3]);
    }

    public static DebitCard createDebitCard(int balance, String... cardinfo) {
        if (cardinfo.length != 3) {
            throw new IllegalArgumentException("There not enough arguments");
        }
        return new DebitCard(balance, cardinfo[0], cardinfo[1], cardinfo[2], cardinfo[3]);
    }

    public static Cash createCash(int balance) {
        return new Cash(balance);
    }

    public static String[] getPaymentMethods() {
        return paymentMethods;
    }
}