package com.sparta.kiosk.service;

import com.sparta.kiosk.domain.Cart;
import com.sparta.kiosk.domain.Order;
import com.sparta.kiosk.domain.UserType;
import com.sparta.kiosk.exception.BadInputException;
import com.sparta.kiosk.exception.ExceptionMessage;
import com.sparta.kiosk.util.ConsoleMessage;
import com.sparta.kiosk.util.InputConsole;
import com.sparta.kiosk.util.OutputConsole;

public class OrderService {
    private static final int ORDER_CODE = 1;        // 주문 코드
    private static final int MODIFY_CODE = 2;       // 수정 코드
    private static final int MENU_CODE = 3;         // 메뉴판 코드

    private static final int ADD_ITEM_CODE = 1;     // 추가 코드
    private static final int REMOVE_ITEM_CODE = 2;  // 빼기 코드
    private static final int CANCEL_CODE = 3;       // 취소 코드
    private final Order order;

    public OrderService(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return this.order;
    }

    /**
     * 주문 기능
     * - 입력값과 일치하는 코드 실행
     * - 메뉴판을 선택하거나, 장바구니가 비었다면 반복문 탈출
     */
    public void proceedOrder() {
        boolean checkOrderContinue;

        do {
            OutputConsole.displayCheckOrder(order);
            int selectOrder = InputConsole.select();

            checkOrderContinue = true;

            switch (selectOrder) {
                case ORDER_CODE -> {
                    UserType userType = handleUserTypeSelection();

                    OutputConsole.displayOrderComplete(order.getDiscountPrice(userType));
                    removeOrder();
                }
                case MODIFY_CODE -> {
                    OutputConsole.displayModifyOrder(order);

                    int selectModify = InputConsole.select();
                    handleModifyOrderSelection(selectModify);
                }
                case MENU_CODE -> checkOrderContinue = false;
                default -> throw new BadInputException(ExceptionMessage.NON_CORRESPONDING_NUM);
            }
        } while (checkOrderContinue && !order.getCarts().isEmpty());

        OutputConsole.displayMessage(ConsoleMessage.MOVE_TO_MENU);
        OutputConsole.displayEmptyLine();
    }

    public void removeOrder() {
        order.removeOrder();
    }

    /**
     * 사용자 유형을 선택받는 구역
     *
     * @return 사용자 유형
     */
    private UserType handleUserTypeSelection() {
        OutputConsole.displayUserDiscountInfo();
        int userTypeNum = InputConsole.select();

        if (userTypeNum == UserType.NATIONAL_MERIT.getCode()) {
            return UserType.NATIONAL_MERIT;
        } else if (userTypeNum == UserType.SOLDIER.getCode()) {
            return UserType.SOLDIER;
        } else if (userTypeNum == UserType.STUDENT.getCode()) {
            return UserType.STUDENT;
        } else if (userTypeNum == UserType.REGULAR.getCode()) {
            return UserType.REGULAR;
        } else {
            throw new BadInputException(ExceptionMessage.INVALID_NUM);
        }
    }

    /**
     * 장바구니에서 입력값과 해당하는 메뉴아이템을 추가, 빼기, 취소할지 선택
     *
     * @param selectModify : 수정하려는 메뉴아이템 번호
     */
    private void handleModifyOrderSelection(int selectModify) {
        Cart cart = order.getCarts().get(selectModify - 1);

        OutputConsole.displayCheckModifyOrder();
        int checkModify = InputConsole.select();

        switch (checkModify) {
            case ADD_ITEM_CODE -> cart.increaseItemQuantity();
            case REMOVE_ITEM_CODE -> {
                if (cart.decreaseItemQuantity()) {
                    order.removeCartItem(cart);
                }
            }
            case CANCEL_CODE -> OutputConsole.displayMessage(ConsoleMessage.CANCEL_COMPLETE);
            default -> throw new BadInputException(ExceptionMessage.NON_CORRESPONDING_NUM);
        }
    }
}