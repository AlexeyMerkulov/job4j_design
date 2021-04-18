package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] container;
    private int count;
    private int modCount;

    public SimpleArray() {
        container = new Object[2];
    }

    public SimpleArray(int length) {
        container = new Object[length];
    }

    public T get(int index) {
        Objects.checkIndex(index, count);
        return (T) container[index];
    }

    public void add(T model) {
        if (count == container.length - 1) {
            container = expand();
        }
        container[count++] = model;
        modCount++;
    }

    public Object[] expand() {
        return Arrays.copyOf(container, container.length * 2);
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {
        int cursor = 0;
        int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return cursor < count;
        }

        @Override
        public T next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) container[cursor++];
        }
    }
}