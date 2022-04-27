package ru.job4j.chapter001.set;

public interface Set<T> extends Iterable {
    boolean add(T value);
    boolean contains(T value);
}
