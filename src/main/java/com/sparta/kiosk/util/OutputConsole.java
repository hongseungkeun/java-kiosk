package com.sparta.kiosk.util;

import com.sparta.kiosk.domain.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

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
    // Kiosk

    /**
     * 메인 메뉴 출력
     *
     * @param menus : 메뉴 리스트
     * @param carts : 장바구니
     * @param orderNum : 장바구니 확인 번호
     * @param cancelNum : 주문 취소 번호
     * @return true : 장바구니에 아이템이 담겨있다, false : 장바구니가 비어있다
     */
    public static boolean displayMainMenu(List<Menu> menus, List<Cart> carts, int orderNum, int cancelNum) {
        displayMessage(LEFT_SQUARE_BRACKET + ConsoleMessage.MAIN_MENU + RIGHT_SQUARE_BRACKET);

        IntStream.range(0, menus.size())
                .forEach(i ->
                        displayCategory(i + 1, menus.get(i)));

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

    /**
     * 카테고리 내 메뉴 아이템 출력
     *
     * @param menu : 메뉴 클래스
     */
    public static void displayCategoryMenu(Menu menu) {
        displayEmptyLine();
        displayMessage(LEFT_SQUARE_BRACKET + menu.category().toUpperCase() + ConsoleMessage.MENU + RIGHT_SQUARE_BRACKET);

        IntStream.range(0, menu.menuItems().size())
                .forEach(i -> {
                    MenuItem menuItem = menu.menuItems().get(i);
                    displayMenuItem(i + 1, menuItem.name(), menuItem.price(), menuItem.description());
                });


        displayMessage(ConsoleMessage.GO_BACK);
    }

    /**
     * 선택한 메뉴 메세지 출력
     *
     * @param name : 이름
     * @param price : 가격
     * @param description : 설명
     */
    public static void displaySelectMenu(String name, double price, String description) {
        displayMessage(ConsoleMessage.SELECT_MENU + name + PRICE_SEPARATOR + price + SEPARATOR + description);
    }

    /**
     * 메뉴를 장바구니에 추가할지 물어보는 메세지 출력
     *
     * @param name : 이름
     * @param price : 가격
     * @param description : 설명
     */
    public static void displayCheckToAdd(String name, double price, String description) {
        displayEmptyLine();
        displayMessage(DOUBLE_QUOTATION_MART + name + PRICE_SEPARATOR + price + SEPARATOR + description + DOUBLE_QUOTATION_MART);
        displayMessage(ConsoleMessage.CHECK_TO_ADD_MENU);
        displayMessage(ConsoleMessage.CONFIRM_BUTTON + TAB + TAB + ConsoleMessage.CANCEL_BUTTON);
    }

    /**
     * 장바구니에 담겼을 때의 메세지 출력
     *
     * @param cart : 장바구니
     */
    public static void displayAddCartComplete(Cart cart) {
        displayEmptyLine();
        displayMessage(cart.getItemName() + ConsoleMessage.ADD_MENU);
        displayMessage(ConsoleMessage.MOVE_TO_MENU);
        displayEmptyLine();
    }

    /**
     * 장바구니에 담지 않았을 때 메세지 출력
     */
    public static void displayCancelMenu() {
        displayEmptyLine();
        displayMessage(ConsoleMessage.CANCEL_COMPLETE);
        displayEmptyLine();
    }

    /**
     * 번호를 붙힌 카테고리 출력
     *
     * @param index : 해당 카테고리 번호
     * @param menu : 메뉴
     */
    private static void displayCategory(int index, Menu menu) {
        displayMessage(index + DOT + menu.category());
    }

    /**
     * 번호를 붙힌 메뉴 아이템 출력
     *
     * @param index : 해당 메뉴 아이템 번호
     * @param name : 메뉴 아이템 이름
     * @param price : 메뉴 아이템 가격
     * @param description : 메뉴 아이템 설명
     */
    private static void displayMenuItem(int index, String name, double price, String description) {
        displayMessage(index + DOT + name + TAB + PRICE_SEPARATOR + price + SEPARATOR + description);
    }


    // OrderService

    /**
     * 현재까지 담긴 장바구니 출력
     *
     * @param order : 주문 클래스
     */
    public static void displayCheckOrder(Order order) {
        displayEmptyLine();
        displayMessage(ConsoleMessage.CHECK_ORDER);
        displayEmptyLine();
        displayMessage(LEFT_SQUARE_BRACKET + ConsoleMessage.ORDER + RIGHT_SQUARE_BRACKET);
        order.getCarts()
                .forEach(i -> displayMessage(i.getItemName() + PRICE_SEPARATOR + i.getItemPrice() + SEPARATOR + i.getItemDescription() + HYPHEN + i.getItemQuantity()));
        displayEmptyLine();
        displayMessage(LEFT_SQUARE_BRACKET + ConsoleMessage.TOTAL + RIGHT_SQUARE_BRACKET);
        displayMessage(PRICE + order.getTotalPrice());
        displayEmptyLine();
        displayMessage(ConsoleMessage.ORDER_BUTTON + TAB + TAB + ConsoleMessage.MODIFY_BUTTON + TAB + TAB + ConsoleMessage.MENU_BUTTON);
    }

    /**
     * 성공적으로 주문이 마무리 됐을 때 출력
     *
     * @param totalPrice : 할인을 거친 주문 가격 총합
     */
    public static void displayOrderComplete(Double totalPrice) {
        displayEmptyLine();
        displayMessage(ConsoleMessage.ORDER_COMPLETE + PRICE + totalPrice + ConsoleMessage.ORDER_COMPLETE_END);
        displayEmptyLine();
    }

    /**
     * 번호를 붙힌 장바구니 출력
     *
     * @param order : 주문 클래스
     */
    public static void displayModifyOrder(Order order) {
        displayEmptyLine();
        displayMessage(ConsoleMessage.MODIFY_MENU);
        displayEmptyLine();
        displayMessage(LEFT_SQUARE_BRACKET + ConsoleMessage.ORDER + RIGHT_SQUARE_BRACKET);
        IntStream.range(0, order.getCarts().size())
                .forEach(i -> {
                    Cart cart = order.getCarts().get(i);
                    displayMessage(i + 1 + DOT + cart.getItemName() + PRICE_SEPARATOR + cart.getItemPrice() + SEPARATOR + cart.getItemDescription() + HYPHEN + cart.getItemQuantity());
                });
    }

    /**
     * 추가, 빼기, 취소 출력
     */
    public static void displayCheckModifyOrder() {
        displayEmptyLine();
        displayMessage(ConsoleMessage.ADD_CART_ITEM + TAB + TAB + ConsoleMessage.REMOVE_CART_ITEM + TAB + TAB + ConsoleMessage.CANCEL_MODIFY_BUTTON);
    }

    /**
     * 사용자 유형별 할인 내용 출력
     */
    public static void displayUserDiscountInfo() {
        displayEmptyLine();
        displayMessage(ConsoleMessage.USER_DISCOUNT_INFO);

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