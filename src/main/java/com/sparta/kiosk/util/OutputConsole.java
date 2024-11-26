package com.sparta.kiosk.util;

import com.sparta.kiosk.domain.Cart;
import com.sparta.kiosk.domain.Menu;
import com.sparta.kiosk.domain.Order;

import java.util.List;

public class OutputConsole {
    private static final String LEFT_SQUARE_BRACKET = "[ ";
    private static final String RIGHT_SQUARE_BRACKET = " ]";
    private static final String DOT = ". ";
    private static final String TAB = "\t";
    private static final String PRICE = "W ";
    private static final String SEPARATOR = " | ";
    private static final String PRICE_SEPARATOR = SEPARATOR + PRICE;
    private static final String DOUBLE_QUOTATION_MART = "\"";

    private OutputConsole() {
    }

    public static void displayMainMenu(List<Menu> menus, List<Cart> carts, int orderNum, int cancelNum) {
        displayMessage(LEFT_SQUARE_BRACKET + ConsoleMessage.MAIN_MENU + RIGHT_SQUARE_BRACKET);

        for (int i = 0; i < menus.size(); i++) {
            displayCategory(i + 1, menus.get(i));
        }

        displayMessage(ConsoleMessage.EXIT);

        if(!carts.isEmpty()) {
            displayEmptyLine();
            displayMessage(LEFT_SQUARE_BRACKET + ConsoleMessage.ORDER_MENU + RIGHT_SQUARE_BRACKET);
            displayMessage(orderNum + DOT + ConsoleMessage.ORDER + TAB + TAB + SEPARATOR + ConsoleMessage.ORDER_MSG);
            displayMessage(cancelNum + DOT + ConsoleMessage.CANCEL + TAB + TAB + SEPARATOR + ConsoleMessage.CANCEL_MSG);
        }
    }

    public static void displayCategoryMenu(String category) {
        displayEmptyLine();
        displayMessage(LEFT_SQUARE_BRACKET + category.toUpperCase() + ConsoleMessage.MENU + RIGHT_SQUARE_BRACKET);
    }

    public static void displayCategory(int index, Menu menu) {
        displayMessage(index + DOT + menu.category());
    }

    public static void displayMenuItem(int index, String name, double price, String description) {
        displayMessage(index + DOT + name + TAB + PRICE_SEPARATOR + price + SEPARATOR + description);
    }

    public static void displaySelectMenu(String name, double price, String description) {
        displayMessage(ConsoleMessage.SELECT_MENU + name + PRICE_SEPARATOR + price + SEPARATOR + description);
    }

    public static void displayCheckToAdd(String name, double price, String description) {
        displayEmptyLine();
        displayMessage(DOUBLE_QUOTATION_MART + name + PRICE_SEPARATOR + price + SEPARATOR + description + DOUBLE_QUOTATION_MART);
        displayMessage(ConsoleMessage.CHECK_TO_ADD_MENU);
        displayMessage(ConsoleMessage.CONFIRM_BUTTON + TAB + TAB + ConsoleMessage.CANCEL_BUTTON);
    }

    public static void displayCheckOrder(Order order) {
        displayEmptyLine();
        displayMessage(ConsoleMessage.CHECK_ORDER);
        displayEmptyLine();
        displayMessage(LEFT_SQUARE_BRACKET + ConsoleMessage.ORDER + RIGHT_SQUARE_BRACKET);
        order.getCarts().forEach(i -> displayMessage(i.getItemName() + PRICE_SEPARATOR + i.getItemPrice() + SEPARATOR + i.getItemDescription()));
        displayEmptyLine();
        displayMessage(LEFT_SQUARE_BRACKET + ConsoleMessage.TOTAL + RIGHT_SQUARE_BRACKET);
        displayMessage(PRICE + order.getTotalPrice());
        displayEmptyLine();
        displayMessage(ConsoleMessage.ORDER_BUTTON + TAB + TAB + ConsoleMessage.MENU_BUTTON);
    }

    public static void displayOrderComplete(Double totalPrice) {
        displayMessage(ConsoleMessage.ORDER_COMPLETE + PRICE + totalPrice + ConsoleMessage.ORDER_COMPLETE_END);
    }

    public static void displayEmptyLine() {
        System.out.println();
    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }
}