package com.sparta.kiosk.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private static final double ROUNDING_FACTOR = 10.0;
    private final List<Cart> carts;
    private double totalPriceCash = 0.0;

    public Order() {
        this.carts = new ArrayList<>();
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public Double getTotalPrice() {
         this.totalPriceCash = Math.round(carts.stream()
                .mapToDouble(cart -> cart.getItemPrice() * cart.getItemQuantity())
                .sum() * ROUNDING_FACTOR) / ROUNDING_FACTOR;
        return this.totalPriceCash;
    }

    public Double getDiscountPrice(UserType userType) {
        return this.totalPriceCash - this.totalPriceCash * userType.getDiscountRate();
    }

    public void addOrder(Cart newCart) {
        carts.stream()
                .filter(cart -> cart.getItemName().equals(newCart.getItemName()))
                .findFirst()
                .ifPresentOrElse(Cart::increaseItemQuantity, () -> carts.add(newCart));
    }

    public void removeCartItem(Cart newCart) {
        carts.removeIf(cart -> cart.getItemName().equals(newCart.getItemName()));
    }

    public void removeOrder() {
        carts.clear();
    }
}