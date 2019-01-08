package com.alpha.decorations;

import com.alpha.enums.WrapperType;

public class Wrapper extends FlowerCompositionDecorator {
    WrapperType wrapperType;

    public Wrapper(FlowerComposition flowerComposition, WrapperType wrapperType) {
        super(flowerComposition);
        this.wrapperType = wrapperType;
    }

    public int calculatePrice() {
        return getFlowerComposition().calculatePrice() + wrapperType.getPrice();
    }

    @Override
    public FlowerComposition getFlowerComposition() {
        return super.getFlowerComposition();
    }

    @Override
    public String toString() {
        return wrapperType.name() + "{" +
                super.toString() +
                "} ";
    }
}
