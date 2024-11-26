package com.sparta.kiosk.util;

import com.sparta.kiosk.exception.ExceptionMessage;

import java.util.Scanner;

public class InputConsole {
    private static final Scanner sc = new Scanner(System.in);

    private InputConsole() {
    }

    public static int select() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            throw new NumberFormatException(ExceptionMessage.INVALID_NUM);
        }
    }
}