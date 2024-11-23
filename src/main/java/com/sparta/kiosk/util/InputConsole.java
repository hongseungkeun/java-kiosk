package com.sparta.kiosk.util;

import java.util.Scanner;

public class InputConsole {
    private static final Scanner sc = new Scanner(System.in);

    private InputConsole() {
    }

    public static int choose() {
        return sc.nextInt();
    }
}
