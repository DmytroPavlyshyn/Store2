package com.alpha;

import com.alpha.decorations.*;
import com.alpha.enums.WrapperType;
import com.alpha.plants.Flower;
import com.alpha.plants.PlantFactory;


import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Flower> flowerList = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            flowerList.add((Flower) PlantFactory.getPlant("Rose"));
        }
        FlowerComposition flowerComposition = new FlowerComposition(flowerList);
        System.out.println("flower composition price: " + flowerComposition.calculatePrice());
        FlowerCompositionDecorator flowerCompositionDecorator = new Wrapper(flowerComposition, WrapperType.POLYETHYLENE);
        System.out.println("flower composition price with cardboard wrapper: " + flowerCompositionDecorator.calculatePrice());
        flowerCompositionDecorator = new Tape(flowerCompositionDecorator);
        System.out.println("flower composition price with cardboard wrapper and tape: " + flowerCompositionDecorator.calculatePrice() );
        flowerCompositionDecorator = new Pot(flowerCompositionDecorator);
        System.out.println("flower composition price with cardboard wrapper and tape and pot: " + flowerCompositionDecorator.calculatePrice() );


    }
}
