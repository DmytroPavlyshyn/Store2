package com.alpha.random;

import com.alpha.Store;
import com.alpha.decorations.*;

import com.alpha.enums.WrapperType;
import com.alpha.plants.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StoreRandomizer {
    Random random;

    public StoreRandomizer(long seed) {
        this.random = new Random(seed);
    }

    List<Plant> plants = new ArrayList<>();
    List<FlowerCompositionDecorator> flowerDecorations = new ArrayList<>();

    public Store nextStore(int numberOfPlants, int numberOfFlowerDecorations) {
        for (int i = 0; i < numberOfPlants; i++) {
            plants.add(random.nextBoolean() ? nextFlower() : nextTree());
        }
        for (int i = 0; i < numberOfFlowerDecorations; i++) {
            flowerDecorations.add(nextFlowerBouquet());
        }
        return new Store(plants, flowerDecorations);
    }

    public Store nextStore(int numberOfFlowers, int numberOfTrees, int numberOfFlowerBouquet) {
        for (int i = 0; i < numberOfFlowers; i++) {
            plants.add(nextFlower());
        }
        for (int i = 0; i < numberOfTrees; i++) {
            plants.add(nextTree());
        }

        for (int i = 0; i < numberOfFlowerBouquet; i++) {
            flowerDecorations.add(nextFlowerBouquet());
        }
        return new Store(plants, flowerDecorations);
    }

    String[] flowerTypes = {"Garden Cactus", "Pot Cactus", "Rose", "Tulip", "Marigold"};

    public Flower nextFlower() {
        String randFlowerType = flowerTypes[random.nextInt(flowerTypes.length)];
        Flower flower = (Flower) PlantFactory.getPlant(randFlowerType);
        return flower;
    }

    public Tree nextTree() {
        return (Tree) PlantFactory.getPlant("Palm");
    }



    public FlowerCompositionDecorator nextFlowerBouquet() {
        List<Flower> flowers = new ArrayList<>();
        int numberOfFlowers = 1 + random.nextInt(4);
        for (int i = 0; i < numberOfFlowers; i++) {
            flowers.add(nextFlower());
        }
        FlowerComposition composition = new FlowerComposition(flowers);
        FlowerCompositionDecorator flowerCompositionDecorator;
        if(random.nextBoolean()) {
            WrapperType[] wrapperTypes = WrapperType.values();
            WrapperType randType = wrapperTypes[random.nextInt(wrapperTypes.length)];

            flowerCompositionDecorator = new Wrapper(composition, randType);
            if (random.nextBoolean()) {
                flowerCompositionDecorator = new Tape(flowerCompositionDecorator);
            }
        }else {
            flowerCompositionDecorator = new Pot(composition);
        }

        return flowerCompositionDecorator;
    }

    public static void main(String[] args) {
        StoreRandomizer storeRandomizer = new StoreRandomizer(47);
        Store store = storeRandomizer.nextStore(10, 10, 4);
        System.out.println(store.getPlants());
        System.out.println("-----------------------------------------------------------------");
        System.out.println(store.getReadyFlowerCompositions());
       // System.out.println(PlantFactory.getPlant("Rose"));
    }

}
