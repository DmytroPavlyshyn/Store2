package com.alpha.decorations;

import com.alpha.Priceable;
import com.alpha.enums.WrapperType;

public  class Wrapper extends FlowerCompositionDecorator{
    WrapperType wrapperType;
    public Wrapper(Priceable priceable,WrapperType wrapperType) {
        super(priceable);
        this.wrapperType = wrapperType;
    }
    public int calculatePrice(){
        return getPriceable().calculatePrice() + wrapperType.getPrice();
    }
}
