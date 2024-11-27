package com.sparta.kiosk.service;

import com.sparta.kiosk.domain.Order;
import com.sparta.kiosk.domain.UserType;
import com.sparta.kiosk.exception.BadInputException;
import com.sparta.kiosk.exception.ExceptionMessage;
import com.sparta.kiosk.util.ConsoleMessage;
import com.sparta.kiosk.util.InputConsole;
import com.sparta.kiosk.util.OutputConsole;

public class OrderService {
    private static final int ORDER_CODE = 1;
    private static final int MENU_CODE = 2;
    private final Order order;

    public OrderService(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return this.order;
    }

    public void proceedOrder() {
        OutputConsole.displayCheckOrder(order);
        int selectOrder = InputConsole.select();

        switch (selectOrder) {
            case ORDER_CODE -> {
                UserType userType = handleUserTypeSelection();

                OutputConsole.displayOrderComplete(order.getDiscountPrice(userType));
                order.removeOrder();
            }
            case MENU_CODE -> {
                OutputConsole.displayMessage(ConsoleMessage.MOVE_TO_MENU);
                OutputConsole.displayEmptyLine();
            }
            default -> throw new BadInputException(ExceptionMessage.NON_CORRESPONDING_NUM);
        }
    }

    public void removeOrder() {
        order.removeOrder();
    }

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
}
