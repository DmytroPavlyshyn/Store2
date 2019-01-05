package com.alpha.plants;

import com.alpha.enums.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;


public class Flower extends Plant {
    String flowerType;
    Color color;
    boolean hasNeedle;

    public Flower( int startingPrice, boolean isNative, double stemLength, Growing growing, String flowerType, Color color, boolean hasNeedle) {
        super(startingPrice, isNative, stemLength, growing);
        this.flowerType = flowerType;
        this.color = color;
        this.hasNeedle = hasNeedle;
    }

    public String getFlowerType() {
        return flowerType;
    }

    public Color getColor() {
        return color;
    }

    public boolean hasNeedle() {
        return hasNeedle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flower)) return false;
        if (!super.equals(o)) return false;
        Flower flower = (Flower) o;
        return hasNeedle == flower.hasNeedle &&
                Objects.equals(getFlowerType(), flower.getFlowerType()) &&
                getColor() == flower.getColor();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getFlowerType(), getColor(), hasNeedle);
    }

    @Override
    public String toString() {
        return flowerType+ "{" +
                " color=" + color +
                ", hasNeedle=" + hasNeedle +
                super.toString()+
                "\n}\n";
    }
}
