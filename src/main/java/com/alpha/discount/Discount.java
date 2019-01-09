package com.alpha.discount;

import com.alpha.Order;

public interface Discount {
    int calculateDiscount(Order order);
}
