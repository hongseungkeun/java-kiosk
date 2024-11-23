package com.sparta.kiosk.service;

import com.sparta.kiosk.domain.Menu;
import com.sparta.kiosk.domain.MenuItem;
import com.sparta.kiosk.util.ConsoleMessage;
import com.sparta.kiosk.util.InputConsole;
import com.sparta.kiosk.util.OutputConsole;

import java.util.InputMismatchException;
import java.util.List;

public class Kiosk {
    private final List<Menu> menus;

    public Kiosk(List<Menu> menus) {
        this.menus = menus;
    }

    public void start() {
        while (true) {
            OutputConsole.printMainMenu();

            for (int i = 0; i < menus.size(); i++) {
                OutputConsole.printCategory(i + 1, menus.get(i));
            }

            OutputConsole.printlnMessage(ConsoleMessage.EXIT);

            try {
                int chooseNum = InputConsole.choose();

                if (chooseNum == 0) {
                    OutputConsole.printlnMessage(ConsoleMessage.EXIT_PROGRAM);
                    break;
                }

                Menu menu = menus.get(chooseNum - 1);
                OutputConsole.printCategoryMenu(menu.category());

                for (int i = 0; i < menu.menuItems().size(); i++) {
                    MenuItem menuItem = menu.menuItems().get(i);
                    OutputConsole.printMenuItem(i + 1, menuItem.name(), menuItem.price(), menuItem.description());
                }

                System.out.println(ConsoleMessage.GO_BACK);
                chooseNum = InputConsole.choose();

                if (chooseNum == 0) {
                    continue;
                }

                MenuItem menuItem = menu.menuItems().get(chooseNum - 1);
                OutputConsole.printChooseMenu(menuItem.name(), menuItem.price(), menuItem.description());

            } catch (IndexOutOfBoundsException e) {
                System.out.println("선택하신 숫자는 없는 숫자입니다.");
            } catch (InputMismatchException e) {
                System.out.println("숫자만 입력해주세요.");
            }
        }
    }
}