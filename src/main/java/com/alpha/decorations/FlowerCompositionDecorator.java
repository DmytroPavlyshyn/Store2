package com.alpha.decorations;


import com.alpha.Priceable;

public abstract class FlowerCompositionDecorator  implements Priceable {
    Priceable priceable;

    public FlowerCompositionDecorator(Priceable priceable) {
        this.priceable = priceable;
    }

    public Priceable getPriceable() {
        return priceable;
    }


}

