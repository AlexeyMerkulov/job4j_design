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
        if (!cache.containsKey(key)) {
            put(key, load(key));
        }
        V strong = cache.get(key).get();
        if (strong == null) {
            put(key, load(key));
        }
        return strong;
    }

    protected abstract V load(K key);
}

