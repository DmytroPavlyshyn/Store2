package com.alpha.decorations;

import com.alpha.Priceable;

public class Pot extends FlowerCompositionDecorator {
    final int potPrice = 25;

    public Pot(Priceable priceable) {
        super(priceable);
    }

    @Override
    public int calculatePrice() {
        return getPriceable().calculatePrice() + potPrice;
    }
}
