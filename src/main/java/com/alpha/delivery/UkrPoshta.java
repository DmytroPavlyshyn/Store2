package com.alpha.delivery;

        import com.alpha.decorations.FlowerComposition;
        import com.alpha.decorations.Priceable;

public class UkrPoshta implements Delivery{
    final int price = 5;
    Priceable priceable;
    @Override
    public void setPriceable(Priceable priceable) {
        this.priceable = priceable;
    }

    @Override
    public int calculateDeliveryPrice() {
        int numberOfFlowers = 1;
        if(priceable instanceof FlowerComposition){
            numberOfFlowers = ((FlowerComposition) priceable).getFlowers().size();
        }
        return  numberOfFlowers*price;
    }

    @Override
    public String toString() {
        return "UkrPoshta{" +
                "price=" + price +
                "}";
    }
}
