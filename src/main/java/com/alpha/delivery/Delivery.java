package com.alpha.delivery;

import com.alpha.decorations.Priceable;

public interface Delivery {
    int calculateDeliveryPrice();
    void setPriceable(Priceable priceable);
}
