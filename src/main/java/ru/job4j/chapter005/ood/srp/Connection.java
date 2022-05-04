package ru.job4j.chapter005.ood.srp;

/**
 * Реализация синглтон является нарушением SRP, т.к. класс Connection не только создает экземпляр класса,
 * но и должен контролировать количество созданных экземпляров.
 */

public class Connection {
    private static Connection instance;

    private Connection() {
    }

    public static Connection getConnection() {
        if (instance == null) {
            instance = new Connection();
        }
        return instance;
    }
}