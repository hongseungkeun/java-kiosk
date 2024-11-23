package com.sparta.kiosk.util;

import com.sparta.kiosk.domain.Menu;

public class OutputConsole {
    private static final String LEFT_SQUARE_BRACKET = "[ ";
    private static final String RIGHT_SQUARE_BRACKET = " ]";
    private static final String DOT = ". ";

    private OutputConsole() {
    }

    public static void printMainMenu() {
        OutputConsole.printlnMessage(LEFT_SQUARE_BRACKET + ConsoleMessage.MAIN_MENU + RIGHT_SQUARE_BRACKET);
    }

    public static void printCategoryMenu(String category) {
        OutputConsole.printlnMessage(LEFT_SQUARE_BRACKET + category.toUpperCase() + ConsoleMessage.MENU + RIGHT_SQUARE_BRACKET);
    }

    public static void printCategory(int index, Menu menu) {
        OutputConsole.printlnMessage(index + DOT + menu.category());
    }

    public static void printMenuItem(int index, String name, double price, String description) {
        OutputConsole.printlnMessage(index + ". " + name + "\t| W " + price + " | " + description);
    }

    public static void printChooseMenu(String name, double price, String description) {
        OutputConsole.printlnMessage(ConsoleMessage.CHOOSE_MENU + name + " | W " + price + " | " + description);
    }

    public static void printlnMessage(String message) {
        System.out.println(message);
    }
}
