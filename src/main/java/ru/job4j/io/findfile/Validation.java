package ru.job4j.io.findfile;

import java.util.HashMap;
import java.util.Map;

public class Validation {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Введите корректное количество входных параметров");
        }
        for (String s : args) {
            String[] rsl = s.split("=");
            if (rsl.length < 2) {
                throw new IllegalArgumentException("Введите параметр работы программы по установленной форме");
            } else {
                values.put(rsl[0].substring(1), rsl[1]);
            }
        }
    }

    public static Validation of(String[] args) {
        Validation names = new Validation();
        names.parse(args);
        return names;
    }
}
