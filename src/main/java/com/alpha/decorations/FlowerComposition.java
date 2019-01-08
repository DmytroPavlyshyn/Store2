package com.alpha.decorations;


import com.alpha.plants.Flower;

import java.util.List;

public class FlowerComposition implements Priceable {
    List<Flower> flowers;

    public List<Flower> getFlowers() {
        return flowers;
    }

    public FlowerComposition(List<Flower> flowers) {
        this.flowers = flowers;
    }

    public int calculatePrice() {
        int price = 0;
        for (Flower flower : getFlowers()) {
            price += flower.calculatePrice();
        }
        return price;
    }

    @Override
    public String toString() {
        return "FlowerComposition{" +
                "flowers=" + flowers +
                '}';
    }
}
