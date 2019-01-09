package com.alpha.payment;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class PaymentMethodFactoryTest {
    @Test
    public void createAllPaymentMethods() {
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        for (String payment : PaymentMethodFactory.getPaymentMethods()) {
            paymentMethods.add(PaymentMethodFactory.getPaymentMethod(payment, 1, "", "", "", ""));
        }
        assertEquals(paymentMethods.size(), PaymentMethodFactory.getPaymentMethods().length);
    }

    @Test
    public void testCreateNotSuitableType() {
        boolean wasThrown = false;
        try {
            PaymentMethodFactory.getPaymentMethod("", 1, "", "", "", "");
        } catch (IllegalArgumentException e) {
            wasThrown = true;
        }
        assertTrue(wasThrown);
    }

}
