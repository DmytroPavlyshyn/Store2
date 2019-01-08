package com.alpha.plants;

import com.alpha.enums.Color;
import com.alpha.enums.Growing;


public class PlantFactory {
    public static Plant getPlant(String s) {
        if (s.matches("Garden Cactus")) {
            return new Flower(120, true, 200, Growing.GARDEN, "Cactus", Color.GREEN, true);
        }
        if (s.matches("Pot Cactus")) {
            return new Flower(30, true, 50, Growing.POT, "Cactus", Color.GREEN, true);
        }
        if (s.matches("Rose")) {
            return new Flower(45, false, 30, Growing.UNDEFINED, "Rose", Color.RED, true);
        }
        if (s.matches("Tulip")) {
            return new Flower(50, false, 30, Growing.UNDEFINED, "Tulip", Color.YELLOW, true);
        }
        if (s.matches("Marigold")) {
            return new Flower(30, false, 30, Growing.UNDEFINED, "Marigold", Color.ORANGE, true);

        }
        if (s.matches("Palm")) {
            return new Tree(100, true, 150, Growing.UNDEFINED, "Palm");
        }
        throw new IllegalArgumentException();
    }

}
