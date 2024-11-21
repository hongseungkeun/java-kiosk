package com.sparta.kiosk.domain;

public class MenuItem {
    private String name;
    private Double price;
    private String description;

    public MenuItem(String name, Double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
