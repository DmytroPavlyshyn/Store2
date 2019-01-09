package com.alpha.discount;

import com.alpha.Order;
import com.alpha.decorations.FlowerCompositionDecorator;
import com.alpha.decorations.Priceable;

public class SimpleDiscount implements Discount {
    @Override
    public int calculateDiscount(Order order) {
        Priceable priceable = order.getPriceable();
        if (priceable instanceof FlowerCompositionDecorator) {
            FlowerCompositionDecorator flowerCompositionDecorator = ((FlowerCompositionDecorator) priceable);
            if (flowerCompositionDecorator.getFlowerComposition().getFlowers().size() > 10) {
                return (int) (order.calculatePrice() * 0.2);
            }
            if (flowerCompositionDecorator.getFlowerComposition().getFlowers().size() > 5) {
                return (int) (order.calculatePrice() * 0.1);
            }
        }

        return 0;
    }


}
