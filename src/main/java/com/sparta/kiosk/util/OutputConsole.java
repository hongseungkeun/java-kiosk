package com.sparta.kiosk.util;

import com.sparta.kiosk.domain.Menu;

public class OutputConsole {
    private static final String LEFT_SQUARE_BRACKET = "[ ";
    private static final String RIGHT_SQUARE_BRACKET = " ]";
    private static final String DOT = ". ";
    private static final String PRICE_SEPARATOR = "\t| W ";
    private static final String ITEM_SEPARATOR = " | ";

    private OutputConsole() {
    }

    public static void displayMainMenu() {
        OutputConsole.displayMessage(LEFT_SQUARE_BRACKET + ConsoleMessage.MAIN_MENU + RIGHT_SQUARE_BRACKET);
    }

    public static void displayCategoryMenu(String category) {
        OutputConsole.displayMessage(LEFT_SQUARE_BRACKET + category.toUpperCase() + ConsoleMessage.MENU + RIGHT_SQUARE_BRACKET);
    }

    public static void displayCategory(int index, Menu menu) {
        OutputConsole.displayMessage(index + DOT + menu.category());
    }

    public static void displayMenuItem(int index, String name, double price, String description) {
        OutputConsole.displayMessage(index + DOT + name + PRICE_SEPARATOR + price + ITEM_SEPARATOR + description);
    }

    public static void displayChooseMenu(String name, double price, String description) {
        OutputConsole.displayMessage(ConsoleMessage.CHOOSE_MENU + name + PRICE_SEPARATOR + price + ITEM_SEPARATOR + description);
    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }
}
