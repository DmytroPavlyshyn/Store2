package com.alpha.payment;

public class PaymentMethodFactory {
    final static String[] paymentMethods = {"Credit", "Debit", "Cash"};

    public static PaymentMethod getPaymentMethod(String type, int balance, String... cardinfo) {
        switch (type) {
            case "Credit":
                return createCreditCard(balance, cardinfo);
            case "Debit":
                return createDebitCard(balance, cardinfo);
            case "Cash":
                return createCash(balance);
            default:
                throw new IllegalArgumentException();
        }
    }

    public static CreditCard createCreditCard(int balance, String[] cardinfo) {
        if (cardinfo.length != 4) {
            throw new IllegalArgumentException("There not enough arguments");
        }
        return new CreditCard(balance, cardinfo[0], cardinfo[1], cardinfo[2], cardinfo[3]);
    }

    public static DebitCard createDebitCard(int balance, String[] cardinfo) {
        if (cardinfo.length != 4) {
            System.out.println(cardinfo.length);
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