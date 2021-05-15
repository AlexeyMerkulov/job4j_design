package ru.job4j.collection;


import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleHashMap<K, V> implements Iterable<SimpleHashMap.Node<K, V>> {
    private final double LOAD_FACTOR = 0.75;
    private Node<K, V>[] container;
    private int count;
    private int modCount;

    public SimpleHashMap() {
        container = new Node[4];
    }

    private int checkKey(K key) {
        int rsl = -1;
        if (key == null) {
            if (container[0] != null) {
                rsl = 0;
            }
            return rsl;
        }
        int index = hash(key.hashCode(), container.length);
        if (container[index] != null) {
            if (Objects.equals(key, container[index].key)) {
                rsl = index;
            } else {
                rsl = -10;
            }
        }
        return rsl;
    }

    private int hash(int hashCode, int containerLength) {
        return hashCode % containerLength;
    }

    private void expand() {
        Node<K, V>[] newContainer = new Node[container.length * 2];
        for (Node<K, V> node : container) {
            if (node != null) {
                if (node.key == null) {
                    newContainer[0] = node;
                } else {
                    newContainer[hash(node.key.hashCode(), newContainer.length)] = node;
                }
            }
        }
        container = newContainer;
    }

    public boolean insert(K key, V value) {
        int index = checkKey(key);
        boolean rsl = true;
        if (index > -1) {
            container[index].value = value;
            modCount++;
        } else if (index == -10) {
            rsl = false;
        } else {
            Node<K, V> node = new Node<>(key, value);
            if (key == null) {
                container[0] = node;
            } else {
                container[hash(key.hashCode(), container.length)] = node;
            }
            count++;
            modCount++;
        }
        if (container.length * LOAD_FACTOR == count + 1) {
            expand();
        }
        return rsl;
    }

    public V get(K key) {
        int index = checkKey(key);
        return index == -1 || index == -10 ? null : container[index].value;
    }

    public boolean delete(K key) {
        int index = checkKey(key);
        boolean rsl = false;
        if (index != -1 && index != -10) {
            container[index] = null;
            count--;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<SimpleHashMap.Node<K, V>> iterator() {
        return new SimpleHashMap<K, V>.Itr();
    }

    private class Itr implements Iterator<Node<K, V>> {
        int cursor = 0;
        int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            boolean rsl = false;
            if (count != 0) {
                for (int i = cursor; i < container.length; i++) {
                    if (container[i] != null) {
                        cursor = i;
                        rsl = true;
                        break;
                    }
                }
            }
            return rsl;
        }

        @Override
        public Node<K, V> next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return container[cursor++];
        }
    }

    static class Node<K, V> {
        K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
