package com.alpha.delivery;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DeliveryFactoryTest {
    @Test
    public void createAllDeliveryMethods() {
        List<Delivery> deliveries = new ArrayList<>();
        for (String delivery : DeliveryFactory.getDeliveryMethods()) {
            deliveries.add(DeliveryFactory.getDelivery(delivery));
        }
        assertEquals(deliveries.size(), DeliveryFactory.getDeliveryMethods().length);
    }

    @Test
    public void testCreateNotSuitableType() {
        boolean wasThrown = false;
        try {
            DeliveryFactory.getDelivery("");
        } catch (IllegalArgumentException e) {
            wasThrown = true;
        }
        assertTrue(wasThrown);
    }

}
