package ru.job4j.chapter001.generics;

public abstract class Base {
    private final String id;

    public Base(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}