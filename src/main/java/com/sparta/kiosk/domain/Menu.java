package com.sparta.kiosk.domain;

import java.util.List;

public record Menu(String category, List<MenuItem> menuItems) {
}