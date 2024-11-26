package com.sparta.kiosk.util;

import com.sparta.kiosk.domain.Menu;

public class OutputConsole {
    private static final String LEFT_SQUARE_BRACKET = "[ ";
    private static final String RIGHT_SQUARE_BRACKET = " ]";
    private static final String DOT = ". ";
    private static final String TAB = "\t";
    private static final String PRICE_SEPARATOR = " | W ";
    private static final String ITEM_SEPARATOR = " | ";
    private static final String DOUBLE_QUOTATION_MART = "\"";

    private OutputConsole() {
    }

    public static void displayMainMenu() {
        OutputConsole.displayMessage(LEFT_SQUARE_BRACKET + ConsoleMessage.MAIN_MENU + RIGHT_SQUARE_BRACKET);
    }

    public static void displayCategoryMenu(String category) {
        displayEmptyLine();
        OutputConsole.displayMessage(LEFT_SQUARE_BRACKET + category.toUpperCase() + ConsoleMessage.MENU + RIGHT_SQUARE_BRACKET);
    }

    public static void displayCategory(int index, Menu menu) {
        OutputConsole.displayMessage(index + DOT + menu.category());
    }

    public static void displayMenuItem(int index, String name, double price, String description) {
        OutputConsole.displayMessage(index + DOT + name + TAB + PRICE_SEPARATOR + price + ITEM_SEPARATOR + description);
    }

    public static void displaySelectMenu(String name, double price, String description) {
        OutputConsole.displayMessage(ConsoleMessage.SELECT_MENU + name + PRICE_SEPARATOR + price + ITEM_SEPARATOR + description);
    }

    public static void displayCheckToAdd(String name, double price, String description) {
        displayEmptyLine();
        OutputConsole.displayMessage(DOUBLE_QUOTATION_MART + name + PRICE_SEPARATOR + price + ITEM_SEPARATOR + description + DOUBLE_QUOTATION_MART);
        OutputConsole.displayMessage(ConsoleMessage.CHECK_TO_ADD_MENU);
        OutputConsole.displayMessage(ConsoleMessage.CONFIRM + TAB + TAB + ConsoleMessage.CANCEL);
    }

    public static void displayEmptyLine() {
        System.out.println();
    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }
}