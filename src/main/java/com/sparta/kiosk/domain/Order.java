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
                .mapToDouble(cart -> cart.getItemPrice() * cart.getItemQuantity())
                .sum();
    }

    public Double getDiscountPrice(UserType userType) {
        return getTotalPrice() - (getTotalPrice() * userType.getDiscountRate());
    }

    public void addOrder(Cart newCart) {
        carts.stream()
                .filter(cart -> cart.getItemName().equals(newCart.getItemName()))
                .findFirst()
                .ifPresentOrElse(Cart::increaseItemQuantity, () -> carts.add(newCart));
    }

    public void removeOrder() {
        carts.clear();
    }
}