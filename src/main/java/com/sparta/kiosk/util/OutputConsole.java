package com.sparta.kiosk.util;

import com.sparta.kiosk.domain.*;

import java.util.Arrays;
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
    private static final String HYPHEN = " - ";
    private static final String COLON = " : ";
    private static final String PERCENT = "%";

    private OutputConsole() {
    }

    public static boolean displayMainMenu(List<Menu> menus, List<Cart> carts, int orderNum, int cancelNum) {
        displayMessage(LEFT_SQUARE_BRACKET + ConsoleMessage.MAIN_MENU + RIGHT_SQUARE_BRACKET);

        for (int i = 0; i < menus.size(); i++) {
            displayCategory(i + 1, menus.get(i));
        }

        displayMessage(ConsoleMessage.EXIT);

        if (!carts.isEmpty()) {
            displayEmptyLine();
            displayMessage(LEFT_SQUARE_BRACKET + ConsoleMessage.ORDER_MENU + RIGHT_SQUARE_BRACKET);
            displayMessage(orderNum + DOT + ConsoleMessage.ORDER + TAB + TAB + SEPARATOR + ConsoleMessage.ORDER_MSG);
            displayMessage(cancelNum + DOT + ConsoleMessage.CANCEL + TAB + TAB + SEPARATOR + ConsoleMessage.CANCEL_MSG);
            return true;
        }
        return false;
    }

    public static void displayCategoryMenu(Menu menu) {
        displayEmptyLine();
        displayMessage(LEFT_SQUARE_BRACKET + menu.category().toUpperCase() + ConsoleMessage.MENU + RIGHT_SQUARE_BRACKET);

        for (int i = 0; i < menu.menuItems().size(); i++) {
            MenuItem menuItem = menu.menuItems().get(i);
            displayMenuItem(i + 1, menuItem.name(), menuItem.price(), menuItem.description());
        }

        displayMessage(ConsoleMessage.GO_BACK);
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

    public static void displayAddCartComplete(Cart cart) {
        displayEmptyLine();
        OutputConsole.displayMessage(cart.getItemName() + ConsoleMessage.ADD_MENU);
        OutputConsole.displayMessage(ConsoleMessage.MOVE_TO_MENU);
        displayEmptyLine();
    }

    public static void displayCheckOrder(Order order) {
        displayEmptyLine();
        displayMessage(ConsoleMessage.CHECK_ORDER);
        displayEmptyLine();
        displayMessage(LEFT_SQUARE_BRACKET + ConsoleMessage.ORDER + RIGHT_SQUARE_BRACKET);
        order.getCarts().forEach(i -> displayMessage(i.getItemName() + PRICE_SEPARATOR + i.getItemPrice() + SEPARATOR + i.getItemDescription() + HYPHEN + i.getItemQuantity()));
        displayEmptyLine();
        displayMessage(LEFT_SQUARE_BRACKET + ConsoleMessage.TOTAL + RIGHT_SQUARE_BRACKET);
        displayMessage(PRICE + order.getTotalPrice());
        displayEmptyLine();
        displayMessage(ConsoleMessage.ORDER_BUTTON + TAB + TAB + ConsoleMessage.MENU_BUTTON);
    }

    public static void displayOrderComplete(Double totalPrice) {
        displayEmptyLine();
        displayMessage(ConsoleMessage.ORDER_COMPLETE + PRICE + totalPrice + ConsoleMessage.ORDER_COMPLETE_END);
        displayEmptyLine();
    }

    public static void displayCancelMenu() {
        OutputConsole.displayEmptyLine();
        OutputConsole.displayMessage(ConsoleMessage.CANCEL_COMPLETE);
        OutputConsole.displayEmptyLine();
    }

    public static void displayUserDiscountInfo() {
        displayEmptyLine();
        OutputConsole.displayMessage(ConsoleMessage.USER_DISCOUNT_INFO);

        Arrays.stream(UserType.values())
                .forEach(type -> displayMessage(type.getCode() + DOT + type.getDescription() + COLON + type.getDiscountRate() * 100 + PERCENT));
    }

    public static void displayEmptyLine() {
        System.out.println();
    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }
}