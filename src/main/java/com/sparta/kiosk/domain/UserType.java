package com.sparta.kiosk.domain;

public enum UserType {
    NATIONAL_MERIT(1, 0.10, "국가유공자"),
    SOLDIER(2, 0.05, "군인"),
    STUDENT(3, 0.03, "학생"),
    REGULAR(4, 0.0, "일반");

    private final int code;
    private final double discountRate;
    private final String description;

    UserType(int code, double discountRate, String description) {
        this.code = code;
        this.discountRate = discountRate;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public String getDescription() {
        return description;
    }
}
