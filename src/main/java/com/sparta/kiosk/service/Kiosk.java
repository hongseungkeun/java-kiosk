package com.sparta.kiosk.service;

import com.sparta.kiosk.domain.Cart;
import com.sparta.kiosk.domain.Menu;
import com.sparta.kiosk.domain.MenuItem;
import com.sparta.kiosk.domain.Order;
import com.sparta.kiosk.exception.BadInputException;
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
    private static final int ORDER_CODE = 1;
    private static final int MENU_CODE = 2;
    private static final int INCREMENT = 1;
    private final List<Menu> menus;
    private final Order order;
    private final int orderNum;
    private final int cancelNum;

    public Kiosk(List<Menu> menus, Order order) {
        this.menus = menus;
        this.order = order;
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

                    int selectOrder = InputConsole.select();

                    if (selectOrder == ORDER_CODE) {
                        OutputConsole.displayOrderComplete(order.getTotalPrice());
                        order.removeOrder();
                        continue;
                    } else if (selectMenu == MENU_CODE) {
                        continue;
                    } else {
                        throw new BadInputException(ExceptionMessage.NON_CORRESPONDING_NUM);
                    }

                } else if (selectMenu == cancelNum) {
                    order.removeOrder();
                    continue;
                }

                Menu menu = menus.get(selectMenu - 1);
                OutputConsole.displayCategoryMenu(menu);

                int selectItem = InputConsole.select();

                if (selectItem == GO_BACK_CODE) {
                    continue;
                }

                MenuItem menuItem = menu.menuItems().get(selectItem - 1);
                OutputConsole.displaySelectMenu(menuItem.name(), menuItem.price(), menuItem.description());

                OutputConsole.displayCheckToAdd(menuItem.name(), menuItem.price(), menuItem.description());

                int selectWhetherToAdd = InputConsole.select();

                if (selectWhetherToAdd == CONFIRM_CODE) {
                    Cart cart = Cart.create(menuItem.name(), menuItem.price(), menuItem.description());
                    order.addOrder(cart);
                    OutputConsole.displayAddCartComplete(cart);
                } else if (selectWhetherToAdd == CANCEL_CODE) {
                    OutputConsole.displayMessage(ConsoleMessage.CANCEL_COMPLETE);
                }

            } catch (IndexOutOfBoundsException e) {
                OutputConsole.displayMessage(ExceptionMessage.NON_CORRESPONDING_NUM);
            } catch (NumberFormatException | BadInputException e) {
                OutputConsole.displayMessage(e.getMessage());
            }
        }
    }
}