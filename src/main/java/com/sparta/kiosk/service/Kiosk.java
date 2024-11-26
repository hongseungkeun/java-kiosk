package com.sparta.kiosk.service;

import com.sparta.kiosk.domain.Cart;
import com.sparta.kiosk.domain.Menu;
import com.sparta.kiosk.domain.MenuItem;
import com.sparta.kiosk.exception.ExceptionMessage;
import com.sparta.kiosk.util.ConsoleMessage;
import com.sparta.kiosk.util.InputConsole;
import com.sparta.kiosk.util.OutputConsole;

import java.util.List;

public class Kiosk {
    private final List<Menu> menus;
    private static final int EXIT_CODE = 0;
    private static final int GO_BACK_CODE = 0;
    private static final int CONFIRM = 1;
    private static final int CANCEL = 2;

    public Kiosk(List<Menu> menus) {
        this.menus = menus;
    }

    public void start() {
        while (true) {
            printMainMenu();

            try {
                int selectCategory = getUserSelect();

                if (selectCategory == EXIT_CODE) {
                    OutputConsole.displayMessage(ConsoleMessage.EXIT_PROGRAM);
                    break;
                }

                Menu menu = menus.get(selectCategory - 1);
                printCategoryMenu(menu);

                int selectItem = getUserSelect();

                if (selectItem == GO_BACK_CODE) {
                    continue;
                }

                MenuItem menuItem = menu.menuItems().get(selectItem - 1);
                OutputConsole.displaySelectMenu(menuItem.name(), menuItem.price(), menuItem.description());

                OutputConsole.displayCheckToAdd(menuItem.name(), menuItem.price(), menuItem.description());

                int selectWhetherToAdd = getUserSelect();

                if (selectWhetherToAdd == CONFIRM) {
                    Cart cart = Cart.addToCart(menuItem.name(), menuItem.price());
                    OutputConsole.displayMessage(cart.getItemName() + ConsoleMessage.ADD_MENU);
                } else if (selectWhetherToAdd == CANCEL) {
                    OutputConsole.displayMessage("취소되었습니다.");
                }

            } catch (IndexOutOfBoundsException e) {
                OutputConsole.displayMessage(ExceptionMessage.NON_CORRESPONDING_NUM);
            } catch (NumberFormatException e) {
                OutputConsole.displayMessage(ExceptionMessage.INVALID_NUM);
            }
        }
    }

    private int getUserSelect() {
        return Integer.parseInt(InputConsole.select());
    }

    private void printMainMenu() {
        OutputConsole.displayMainMenu();

        for (int i = 0; i < menus.size(); i++) {
            OutputConsole.displayCategory(i + 1, menus.get(i));
        }

        OutputConsole.displayMessage(ConsoleMessage.EXIT);
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