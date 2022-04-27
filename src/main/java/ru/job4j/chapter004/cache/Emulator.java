package ru.job4j.chapter004.cache;

import java.util.Scanner;

public class Emulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Укажите кэшируемую директорию");
        String directoryName = scanner.next();
        AbstractCache<String, String> cache = new DirFileCache(directoryName);
        boolean run = true;
        while (run) {
            System.out.println("Укажите имя файла");
            String fileName = scanner.next();
            System.out.println("Нажмите 1 для загрузки содержимого в кэш" + System.lineSeparator()
                    + "Нажмите 2 для получения содержимого из кэша");
            int task = scanner.nextInt();
            if (task == 1) {
                cache.put(fileName, cache.load(fileName));
            } else if (task == 2) {
                System.out.println(cache.get(fileName));
            }
            System.out.println("Нажмите 3 для продолжения работы или 4 для выхода");
            int escape = scanner.nextInt();
            if (escape == 4) {
                run = false;
            }
        }
    }
}