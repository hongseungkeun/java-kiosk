package com.sparta.kiosk.domain;

public class Cart {
    private static final Integer DEFAULT_ITEM_QUANTITY = 1;

    private String itemName;
    private Integer itemQuantity;
    private Double itemPrice;
    private String itemDescription;

    private Cart(String itemName, Integer itemQuantity, Double itemPrice, String itemDescription) {
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.itemPrice = itemPrice;
        this.itemDescription = itemDescription;
    }

    public static Cart addToCart(String itemName, Double itemPrice, String itemDescription) {
        return new Cart(itemName, DEFAULT_ITEM_QUANTITY, itemPrice, itemDescription);
    }

    public String getItemName() {
        return itemName;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public String getItemDescription() {
        return itemDescription;
    }
}