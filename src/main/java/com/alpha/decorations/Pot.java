package com.alpha.decorations;

public class Pot extends FlowerCompositionDecorator {
    final int potPrice = 25;

    public Pot(FlowerComposition priceable) {
        super(priceable);
    }

    @Override
    public int calculatePrice() {
        return getFlowerComposition().calculatePrice() + potPrice;
    }

    @Override
    public FlowerComposition getFlowerComposition() {
        return super.getFlowerComposition();
    }
}
