package ru.job4j.chapter005.isp.menu;

public class SimpleMenuPrinter implements MenuPrinter {

    @Override
    public void print(Menu menu) {
        menu.forEach(x -> {
            int numberLength = x.getNumber().length();
            if (numberLength > 2) {
                System.out.println("-".repeat(numberLength) + x.getNumber() + x.getName());
            } else {
                System.out.println(x.getNumber() + x.getName());
            }
        });
    }
}
