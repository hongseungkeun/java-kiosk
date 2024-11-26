package com.sparta.kiosk.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<Cart> carts;

    public Order() {
        this.carts = new ArrayList<>();
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public Double getTotalPrice() {
        return carts.stream()
                .mapToDouble(Cart::getItemPrice)
                .sum();
    }

    public void addOrder(Cart cart) {
        carts.add(cart);
    }

    public void removeOrder() {
        carts.clear();
    }
}