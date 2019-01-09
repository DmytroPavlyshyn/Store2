package com.alpha.decorations;


public class Tape extends FlowerCompositionDecorator {
    public static final int tapePrice = 10;

    public Tape(FlowerComposition flowerComposition) {
        super(flowerComposition);
    }

    @Override
    public int calculatePrice() {
        if (flowerComposition instanceof FlowerCompositionDecorator)
            return  flowerComposition.calculatePrice() + tapePrice;

        return getFlowerComposition().calculatePrice() + tapePrice;
    }

    @Override
    public FlowerComposition getFlowerComposition() {
        return super.getFlowerComposition();
    }

    @Override
    public String toString() {
        return "Tape{" +
                "tapePrice=" + tapePrice +
                "} " + super.toString();
    }
}
