package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

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
            Object[] newContainer = new Object[container.length * 2];
            System.arraycopy(container, 0, newContainer, 0, container.length);
            container = newContainer;
        }
        container[count++] = model;
        modCount++;
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