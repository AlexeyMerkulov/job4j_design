package ru.job4j.chapter004.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        cache.put(key, new SoftReference<V>(value));
    }

    public V get(K key) {
        SoftReference<V> nullCheck = cache.getOrDefault(key, new SoftReference<V>(null));
        V strong = nullCheck.get();
        if (strong == null) {
            strong = load(key);
            put(key, strong);
        }
        return strong;
    }

    protected abstract V load(K key);
}