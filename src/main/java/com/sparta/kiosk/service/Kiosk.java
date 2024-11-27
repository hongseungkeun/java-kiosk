package com.sparta.kiosk.service;

import com.sparta.kiosk.domain.Cart;
import com.sparta.kiosk.domain.Menu;
import com.sparta.kiosk.domain.MenuItem;
import com.sparta.kiosk.exception.BadInputException;
import com.sparta.kiosk.exception.ExceptionMessage;
import com.sparta.kiosk.util.ConsoleMessage;
import com.sparta.kiosk.util.InputConsole;
import com.sparta.kiosk.util.OutputConsole;

import java.util.List;

public class Kiosk {
    private static final int EXIT_CODE = 0;     // 종료 코드
    private static final int GO_BACK_CODE = 0;  // 뒤로 가기 코드
    private static final int CONFIRM_CODE = 1;  // 확인 코드
    private static final int CANCEL_CODE = 2;   // 취소 코드
    private static final int INCREMENT = 1;     // 사이즈를 1 증가시키기 위한 상수
    private final List<Menu> menus;
    private final OrderService orderService;
    private final int orderNum;
    private final int cancelNum;

    public Kiosk(List<Menu> menus, OrderService orderService) {
        this.menus = menus;
        this.orderService = orderService;
        this.orderNum = menus.size() + INCREMENT;
        this.cancelNum = orderNum + INCREMENT;
    }

    /**
     * 키오스크 앱을 종료 코드를 받기전까지 반복
     */
    public void start() {
        while (true) {
            boolean isPossibleOrder = OutputConsole.displayMainMenu(menus, orderService.getOrder().getCarts(), orderNum, cancelNum);

            try {
                int selectMenu = InputConsole.select();

                if (handleMainMenuSelection(selectMenu, isPossibleOrder)) {
                    break;
                }
            } catch (IndexOutOfBoundsException e) {
                OutputConsole.displayMessage(ExceptionMessage.NON_CORRESPONDING_NUM);
                OutputConsole.displayEmptyLine();
            } catch (NumberFormatException | BadInputException e) {
                OutputConsole.displayMessage(e.getMessage());
                OutputConsole.displayEmptyLine();
            }
        }
    }

    /**
     * 선택한 코드 실행
     *
     * @param selectMenu : 선택한 메뉴 or 카테고리 번호
     * @param isPossibleOrder   : 주문이 가능한지 여부
     * @return true : 키오스크 종료, false: 키오스크 반복
     */
    private boolean handleMainMenuSelection(int selectMenu, boolean isPossibleOrder) {
        if (selectMenu == EXIT_CODE) {
            OutputConsole.displayMessage(ConsoleMessage.EXIT_PROGRAM);
            return true;
        }

        if (isPossibleOrder) {
            if (selectMenu == orderNum) {
                orderService.proceedOrder();

                return false;
            } else if (selectMenu == cancelNum) {
                orderService.removeOrder();
                OutputConsole.displayCancelMenu();

                return false;
            }
        }

        handleMenuSelection(selectMenu);
        return false;
    }

    /**
     * 입력받은 값에 해당하는 카테고리에서 장바구니에 담을 메뉴아이템 선택
     *
     * @param selectMenu : 선택한 메뉴 or 카테고리 번호
     */
    private void handleMenuSelection(int selectMenu) {
        Menu menu = menus.get(selectMenu - 1);
        OutputConsole.displayCategoryMenu(menu);

        int selectItem = InputConsole.select();

        if (selectItem == GO_BACK_CODE) {
            return;
        }

        handleMenuItemSelection(menu, selectItem);
    }

    /**
     * 메뉴아이템을 장바구니에 담을지 취소할지 선택
     *
     * @param menu : 선택한 메뉴
     * @param selectItem : 선택한 메뉴아이템
     */
    private void handleMenuItemSelection(Menu menu, int selectItem) {
        MenuItem menuItem = menu.menuItems().get(selectItem - 1);
        OutputConsole.displaySelectMenu(menuItem.name(), menuItem.price(), menuItem.description());
        OutputConsole.displayCheckToAdd(menuItem.name(), menuItem.price(), menuItem.description());

        int selectWhetherToAdd = InputConsole.select();

        switch (selectWhetherToAdd) {
            case CONFIRM_CODE -> {
                Cart cart = Cart.create(menuItem.name(), menuItem.price(), menuItem.description());
                orderService.getOrder().addOrder(cart);
                OutputConsole.displayAddCartComplete(cart);
            }
            case CANCEL_CODE -> OutputConsole.displayCancelMenu();
            default -> throw new BadInputException(ExceptionMessage.NON_CORRESPONDING_NUM);
        }
    }
}