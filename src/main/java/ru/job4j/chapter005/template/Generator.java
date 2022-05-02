package ru.job4j.chapter005.template;

import java.util.Map;

public interface Generator {
    String produce(String template, Map<String, String> args);
}
