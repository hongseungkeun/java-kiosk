package com.sparta.kiosk.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private static final double ROUNDING_FACTOR = 10.0; // 소수점 첫자리까지 반올림 하기 위한 변수
    private final List<Cart> carts;
    private double totalPriceCash = 0.0;    // 총합 금액 캐싱

    public Order() {
        this.carts = new ArrayList<>();
    }

    public List<Cart> getCarts() {
        return carts;
    }

    /**
     * 소수점 첫자리까지 반올림 한 총합 금액을 저장해둔 뒤 반환
     *
     * @return 총합 금액
     */
    public Double getTotalPrice() {
         this.totalPriceCash = Math.round(carts.stream()
                .mapToDouble(cart -> cart.getItemPrice() * cart.getItemQuantity())
                .sum() * ROUNDING_FACTOR) / ROUNDING_FACTOR;
        return this.totalPriceCash;
    }

    /**
     *
     * @param userType : 사용자 유형
     * @return 사용자 유형별 할인율을 적용한 총합 금액
     */
    public Double getDiscountPrice(UserType userType) {
        return this.totalPriceCash - this.totalPriceCash * userType.getDiscountRate();
    }

    /**
     * 장바구니에 담긴 메뉴아이템과 새로 추가된 메뉴아이템의 이름이 같다면 수량 증가, 없다면 추가
     *
     * @param newCart : 장바구니에 새로 추가된 메뉴아이템
     */
    public void addOrder(Cart newCart) {
        carts.stream()
                .filter(cart -> cart.getItemName().equals(newCart.getItemName()))
                .findFirst()
                .ifPresentOrElse(Cart::increaseItemQuantity, () -> carts.add(newCart));
    }

    /**
     * 장바구니에 이름이 같은 메뉴아이템이 있다면 제거
     */
    public void removeCartItem(Cart newCart) {
        carts.removeIf(cart -> cart.getItemName().equals(newCart.getItemName()));
    }

    public void removeOrder() {
        carts.clear();
    }
}