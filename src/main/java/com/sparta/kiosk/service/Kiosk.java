package com.sparta.kiosk.service;

import com.sparta.kiosk.domain.Cart;
import com.sparta.kiosk.domain.Menu;
import com.sparta.kiosk.domain.MenuItem;
import com.sparta.kiosk.domain.Order;
import com.sparta.kiosk.exception.ExceptionMessage;
import com.sparta.kiosk.util.ConsoleMessage;
import com.sparta.kiosk.util.InputConsole;
import com.sparta.kiosk.util.OutputConsole;

import java.util.List;

public class Kiosk {
    private static final int EXIT_CODE = 0;
    private static final int GO_BACK_CODE = 0;
    private static final int CONFIRM_CODE = 1;
    private static final int CANCEL_CODE = 2;
    private static final int INCREMENT = 1;
    private static Order order = new Order();
    private final List<Menu> menus;
    private final int orderNum;
    private final int cancelNum;

    public Kiosk(List<Menu> menus) {
        this.menus = menus;
        this.orderNum = menus.size() + INCREMENT;
        this.cancelNum = orderNum + INCREMENT;
    }

    public void start() {
        while (true) {
            OutputConsole.displayMainMenu(menus, order.getCarts(), orderNum, cancelNum);

            try {
                int selectMenu = InputConsole.select();

                if (selectMenu == EXIT_CODE) {
                    OutputConsole.displayMessage(ConsoleMessage.EXIT_PROGRAM);
                    break;
                } else if (selectMenu == orderNum) {
                    OutputConsole.displayCheckOrder(order);
                } else if (selectMenu == cancelNum) {
                    order.removeOrder();
                }

                Menu menu = menus.get(selectMenu - 1);
                printCategoryMenu(menu);

                int selectItem = InputConsole.select();

                if (selectItem == GO_BACK_CODE) {
                    continue;
                }

                MenuItem menuItem = menu.menuItems().get(selectItem - 1);
                OutputConsole.displaySelectMenu(menuItem.name(), menuItem.price(), menuItem.description());

                OutputConsole.displayCheckToAdd(menuItem.name(), menuItem.price(), menuItem.description());

                int selectWhetherToAdd = InputConsole.select();

                if (selectWhetherToAdd == CONFIRM_CODE) {
                    Cart cart = Cart.addToCart(menuItem.name(), menuItem.price(), menuItem.description());
                    order.addOrder(cart);
                    OutputConsole.displayMessage(cart.getItemName() + ConsoleMessage.ADD_MENU);
                } else if (selectWhetherToAdd == CANCEL_CODE) {
                    OutputConsole.displayMessage("취소되었습니다.");
                }

            } catch (IndexOutOfBoundsException e) {
                OutputConsole.displayMessage(ExceptionMessage.NON_CORRESPONDING_NUM);
            } catch (NumberFormatException e) {
                OutputConsole.displayMessage(e.getMessage());
            }
        }
    }

    private void printCategoryMenu(Menu menu) {
        OutputConsole.displayCategoryMenu(menu.category());

        for (int i = 0; i < menu.menuItems().size(); i++) {
            MenuItem menuItem = menu.menuItems().get(i);
            OutputConsole.displayMenuItem(i + 1, menuItem.name(), menuItem.price(), menuItem.description());
        }

        OutputConsole.displayMessage(ConsoleMessage.GO_BACK);
    }
}