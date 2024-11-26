package com.sparta.kiosk;

import com.sparta.kiosk.domain.Menu;
import com.sparta.kiosk.domain.Order;
import com.sparta.kiosk.factory.MenuFactory;
import com.sparta.kiosk.service.Kiosk;
import com.sparta.kiosk.service.OrderService;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        Menu burgerMenu = MenuFactory.createBurgerMenu();
        Menu drinkMenu = MenuFactory.createDrinkMenu();
        Menu dessertMenu = MenuFactory.createDessertMenu();
        Kiosk kiosk = new Kiosk(List.of(burgerMenu, drinkMenu, dessertMenu), new OrderService(new Order()));

        kiosk.start();
    }
}