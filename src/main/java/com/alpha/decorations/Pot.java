package com.alpha.decorations;

public class Pot extends FlowerCompositionDecorator {
    public static final int potPrice = 25;

    public Pot(FlowerComposition priceable) {
        super(priceable);
    }

    @Override
    public int calculatePrice() {
        if (flowerComposition instanceof FlowerCompositionDecorator)
            return  flowerComposition.calculatePrice() + potPrice;
        return getFlowerComposition().calculatePrice() + potPrice;
    }

    @Override
    public FlowerComposition getFlowerComposition() {
        return super.getFlowerComposition();
    }
}
