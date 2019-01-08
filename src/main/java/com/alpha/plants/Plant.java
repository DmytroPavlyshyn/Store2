package com.alpha.plants;

import com.alpha.decorations.Priceable;
import com.alpha.enums.Growing;

import java.time.LocalDateTime;
import java.util.Objects;

public class Plant implements Priceable {
    LocalDateTime shippingTime;
    int startingPrice;
    boolean isNative;
    double stemLength;
    Growing growing;

    public Plant(int startingPrice, boolean isNative, double stemLength, Growing growing) {
        this.startingPrice = startingPrice;
        this.isNative = isNative;
        this.stemLength = stemLength;
        this.growing = growing;
        this.shippingTime = LocalDateTime.now();
    }

    public LocalDateTime getShippingTime() {
        return shippingTime;
    }

    public int calculatePrice() {
        return startingPrice;
    }

    public boolean isNative() {
        return isNative;
    }

    public double getStemLength() {
        return stemLength;
    }

    public Growing getGrowing() {
        return growing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plant)) return false;
        Plant plant = (Plant) o;
        return calculatePrice() == plant.calculatePrice() &&
                isNative() == plant.isNative() &&
                Double.compare(plant.getStemLength(), getStemLength()) == 0 &&
                Objects.equals(getShippingTime(), plant.getShippingTime()) &&
                getGrowing() == plant.getGrowing();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getShippingTime(), calculatePrice(), isNative(), getStemLength(), getGrowing());
    }

    @Override
    public String toString() {
        return
                "\nshippingTime=" + shippingTime +
                        ", \nstartingPrice=" + startingPrice +
                        ", \nisNative=" + isNative +
                        ", \nstemLength=" + stemLength +
                        ", \ngrowing=" + growing + "\n";
    }
}
