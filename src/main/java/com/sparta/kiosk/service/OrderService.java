package com.sparta.kiosk.service;

import com.sparta.kiosk.domain.Order;
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
                OutputConsole.displayOrderComplete(order.getTotalPrice());
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
}
