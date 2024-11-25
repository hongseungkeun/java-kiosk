package com.sparta.kiosk.service;

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

    public Kiosk(List<Menu> menus) {
        this.menus = menus;
    }

    public void start() {
        while (true) {
            printMainMenu();

            try {
                int categoryNum = getUserChoice();

                if (categoryNum == EXIT_CODE) {
                    OutputConsole.displayMessage(ConsoleMessage.EXIT_PROGRAM);
                    break;
                }

                Menu menu = menus.get(categoryNum - 1);
                printCategoryMenu(menu);

                int itemNum = getUserChoice();

                if (itemNum == GO_BACK_CODE) {
                    continue;
                }

                MenuItem menuItem = menu.menuItems().get(itemNum - 1);
                OutputConsole.displayChooseMenu(menuItem.name(), menuItem.price(), menuItem.description());

            } catch (IndexOutOfBoundsException e) {
                OutputConsole.displayMessage(ExceptionMessage.NON_CORRESPONDING_NUM);
            } catch (NumberFormatException e) {
                OutputConsole.displayMessage(ExceptionMessage.INVALID_NUM);
            }
        }
    }

    private int getUserChoice() {
        return Integer.parseInt(InputConsole.choose());
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