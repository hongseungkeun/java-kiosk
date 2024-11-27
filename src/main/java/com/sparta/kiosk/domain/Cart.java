package com.sparta.kiosk.domain;

public class Cart {
    private static final Integer DEFAULT_ITEM_QUANTITY = 1;

    private final String itemName;
    private Integer itemQuantity;
    private final Double itemPrice;
    private final String itemDescription;

    private Cart(String itemName, Integer itemQuantity, Double itemPrice, String itemDescription) {
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.itemPrice = itemPrice;
        this.itemDescription = itemDescription;
    }

    public static Cart create(String itemName, Double itemPrice, String itemDescription) {
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

    public void increaseItemQuantity() {
        this.itemQuantity += 1;
    }

    public boolean decreaseItemQuantity() {
        this.itemQuantity -= 1;

        return this.itemQuantity == 0;
    }
}