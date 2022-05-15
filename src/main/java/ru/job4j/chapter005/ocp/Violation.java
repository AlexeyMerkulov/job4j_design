package ru.job4j.chapter005.ocp;

import java.util.ArrayList;

public class Violation {

    /**
    ссылка cat не абстрактного типа а конкретной реализации
     */
    private Cat cat;

    private interface Animal { }

    private static class Cat implements Animal { }

    /**
     для птицы и самолета можно сделать один интерфейс Flyable
     */
    private static class Bird {
        public String fly() {
            return "Кар-кар";
        }
    }

    private static class Plane {
        public String fly() {
            return "Вжжжжж";
        }
    }

    /**
     Возвращаем реализацию а не абстракцию
     */
    public ArrayList<String> getArrayList() {
        return new ArrayList<>();
    }
}
