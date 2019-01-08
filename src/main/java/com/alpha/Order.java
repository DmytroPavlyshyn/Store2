package com.alpha;

import com.alpha.decorations.Priceable;
import com.alpha.delivery.Delivery;
import com.alpha.payment.PaymentMethod;

public class Order implements Priceable {
    private Priceable priceable;
    private Delivery deliveryMethod;
    private PaymentMethod paymentMethod;

    public Order(Priceable priceable, Delivery deliveryMethod, PaymentMethod paymentMethod) {
        this.priceable = priceable;
        this.deliveryMethod = deliveryMethod;
        this.paymentMethod = paymentMethod;
    }

    public Priceable getPriceable() {
        return priceable;
    }

    public Delivery getDeliveryMethod() {
        return deliveryMethod;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    @Override
    public int calculatePrice() {
        deliveryMethod.setPriceable(priceable);
        int deliveryPrice = deliveryMethod.calculateDeliveryPrice();
        return priceable.calculatePrice()+deliveryPrice;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Order{");
        sb.append("priceable=").append(priceable);
        sb.append(", deliveryMethod=").append(deliveryMethod);
        sb.append(", paymentMethod=").append(paymentMethod);
        sb.append('}');
        return sb.toString();
    }
}
