package com.alpha.decorations;

import com.alpha.Priceable;

public class Tape extends FlowerCompositionDecorator {
    final int tapePrice = 10;

    public Tape(Priceable priceable) {
        super(priceable);
    }

    @Override
    public int calculatePrice() {
        return getPriceable().calculatePrice() + tapePrice;
    }
}
