package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements Iterable<E> {
    private int count;
    private int modCount;
    private Node<E> first;
    private Node<E> last;


    public void add(E value) {
        Node<E> newNode;
        if (count == 0) {
            newNode = new Node<>(value, null, null);
            first = newNode;
        } else {
            newNode = new Node<>(value, last, null);
            last.next = newNode;
        }
        last = newNode;
        count++;
        modCount++;
    }

    public E get(int index) {
        Objects.checkIndex(index, count);
        Node<E> element = first;
        for (int i = 1; i <= index; i++) {
            element = element.next;
        }
        return element.item;
    }

    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {
        int cursor = 0;
        int expectedModCount = modCount;
        Node<E> element = first;

        @Override
        public boolean hasNext() {
            return cursor < count;
        }

        @Override
        public E next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (cursor != 0) {
                element = element.next;
            }
            cursor++;
            return element.item;
        }
    }

    private class Node<E> {
        private E item;
        private Node<E> previous;
        private Node<E> next;

        Node(E item, Node<E> previous, Node<E> next) {
            this.item = item;
            this.previous = previous;
            this.next = next;
        }
    }
}