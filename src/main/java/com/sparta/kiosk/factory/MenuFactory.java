package com.sparta.kiosk.factory;

import com.sparta.kiosk.domain.Menu;
import com.sparta.kiosk.domain.MenuItem;

import java.util.List;

public class MenuFactory {
    public static Menu createBurgerMenu() {
        return new Menu("Burgers", List.of(
                new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"),
                new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"),
                new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"),
                new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거")
        ));
    }

    public static Menu createDrinkMenu() {
        return new Menu("Drinks", List.of(
                new MenuItem("Sprite", 2.0, "카페인이 없는 무색 투명의 탄산음료"),
                new MenuItem("CocaCola", 2.0, "캐러멜 색소와 여러 첨가물을 넣고 탄산을 더해 만든 탄산음료"),
                new MenuItem("Pepsi", 2.0, "달콤함과 부드러운 탄산감이 어우러진 탄산음료")
        ));
    }

    public static Menu createDessertMenu() {
        return new Menu("Desserts", List.of(
                new MenuItem("TiramisuCake", 5.9, "마스카포네 치즈 크림에 커피 시트를 얹은 티라미수"),
                new MenuItem("CreamCake", 5.9, "달콤한 초코릿 케이크시트에 가나슈 생크림과 다크 초콜릿을 토핑한 달달한 케이크"),
                new MenuItem("CheeseCake", 5.5, "진한 치즈맛을 느낄 수 있는 케이크"),
                new MenuItem("StickEggTart", 4.6, "달콤하고 고소한 맛의 에그 타르트"),
                new MenuItem("EggSandwich", 4.4, "화이트 식빵 사이에 고소한 에그 스프레드를 넣은 부드러운 샌드위치")
        ));
    }
}