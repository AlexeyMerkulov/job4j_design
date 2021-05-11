package ru.job4j.collection;


import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleHashMap<K, V> implements Iterable<SimpleHashMap.Node> {
    private Node<K, V>[] container;
    private int count;
    private int modCount;

    public SimpleHashMap() {
        container = new Node[4];
    }

    private int checkKey(K key) {
        int rsl = -1;
        for (int i = 0; i < container.length; i++) {
            if (container[i] != null) {
                if (key == null && container[i].key == null) {
                    rsl = i;
                    break;
                } else if (key != null && hash(key.hashCode(), container.length) == container[i].hash) {
                    if (Objects.equals(key, container[i].key)) {
                        rsl = i;
                        break;
                    }
                    rsl = -10;
                }
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
                newContainer[hash(node.key.hashCode(), newContainer.length)] = node;
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
            container[node.hash] = node;
            count++;
            modCount++;
        }
        if (container.length * 0.75 == count + 1) {
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
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<SimpleHashMap.Node> iterator() {
        return new SimpleHashMap.Itr();
    }

    private class Itr implements Iterator<Node> {
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
        public Node next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return container[cursor++];
        }
    }

    class Node<K, V> {
        K key;
        V value;
        int hash;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            if (key == null) {
                hash = 0;
            } else {
                hash = hash(key.hashCode(), container.length);
            }
        }
    }
}
