package com.alpha.decorations;


public class Tape extends FlowerCompositionDecorator {
    final int tapePrice = 10;

    public Tape(FlowerComposition flowerComposition) {
        super(flowerComposition);
    }

    @Override
    public int calculatePrice() {
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
