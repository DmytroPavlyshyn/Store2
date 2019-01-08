package com.alpha.delivery;

public class DeliveryFactory {
    final static String[] deliveryMethods = {"UkrPoshta", "NovaPoshta", "Corrier"};

    public static Delivery getDelivery(String type) {
        switch (type) {
            case "UkrPoshta":
                return new UkrPoshta();
            case "NovaPoshta":
                return new NovaPoshta();
            case "Corrier":
                return new Corrier();
            default:
                throw new IllegalArgumentException();
        }

    }

    public static String[] getDeliveryMethods() {
        return deliveryMethods;
    }
}
