package ru.job4j.serialization.json;

public class Engine {

    private final String type;

    public Engine(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "type='" + type + '\''
                + '}';
    }
}
