package com.sparta.kiosk.service;

import com.sparta.kiosk.domain.Menu;
import com.sparta.kiosk.domain.MenuItem;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    private final List<Menu> menus;

    public Kiosk(List<Menu> menus) {
        this.menus = menus;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        int choose;

        while (true) {
            System.out.println("[ MAIN MENU ]");
            for (int i = 0; i < menus.size(); i++) {
                System.out.println(i + 1 + ". " + menus.get(i).category());
            }

            System.out.println("0. 종료");
            choose = sc.nextInt();

            if (choose == 0) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            try {
                Menu menu = menus.get(choose - 1);

                System.out.println("[ BURGERS MENU ]");
                for (int i = 0; i < menu.menuItems().size(); i++) {
                    MenuItem menuItem = menu.menuItems().get(i);
                    System.out.println(i + 1 + ". " + menuItem.name() + "\t| W " + menuItem.price() + " | " + menuItem.description());
                }
                System.out.println("0. 뒤로가기");

                choose = sc.nextInt();

                MenuItem menuItem = menu.menuItems().get(choose - 1);
                System.out.println("선택한 메뉴: " + menuItem.name() + " | W " + menuItem.price() + " | " + menuItem.description());

            } catch (IndexOutOfBoundsException e) {
                System.out.println("선택하신 숫자는 없는 숫자입니다.");
            } catch (InputMismatchException e) {
                System.out.println("숫자만 입력해주세요.");
            }
        }
    }
}