package com.alpha.enums;


import java.io.Serializable;

public enum WrapperType implements Serializable {
    PAPER(5), CARDBOARD(10), POLYETHYLENE(20), NONE(0);
    int price;

    WrapperType(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer(this.name());
        sb.append("{price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}
