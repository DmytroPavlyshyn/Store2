package com.alpha.decorations;


public abstract class FlowerCompositionDecorator extends FlowerComposition {
    FlowerComposition flowerComposition;

    public FlowerCompositionDecorator(FlowerComposition flowerComposition) {
        super(flowerComposition.getFlowers());
        this.flowerComposition = flowerComposition;
    }

    public FlowerComposition getFlowerComposition() {
        if (flowerComposition instanceof FlowerCompositionDecorator)
            return ((FlowerCompositionDecorator) flowerComposition).getFlowerComposition();
        return flowerComposition;
    }



    @Override
    public String toString() {
        return "FlowerCompositionDecorator{" +
                "flowerComposition=" + flowerComposition +
                '}';
    }
}

