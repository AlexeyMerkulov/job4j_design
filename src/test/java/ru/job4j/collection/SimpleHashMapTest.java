package ru.job4j.collection;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class SimpleHashMapTest {

    @Test
    public void insertAndGetTest() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "one");
        map.insert(2, "two");
        map.insert(3, "three");
        String rsl = map.get(1) + " " + map.get(2) + " " + map.get(3);
        assertThat(rsl, is("one two three"));
    }

    @Test
    public void insertSameKeys() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "one");
        map.insert(1, "two");
        String rsl = map.get(1);
        assertThat(rsl, is("two"));
    }

    @Test
    public void whenExpand() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "one");
        map.insert(2, "two");
        map.insert(3, "three");
        map.insert(4, "four");
        map.insert(5, "five");
        map.insert(6, "six");
        map.insert(7, "seven");
        String rsl = map.get(7);
        assertThat(rsl, is("seven"));
    }

    @Test
    public void whenInsertNull() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(null, "one");
        map.insert(null, "two");
        String rsl = map.get(null);
        assertThat(rsl, is("two"));
    }

    @Test
    public void whenInsertFalse() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "one");
        assertFalse(map.insert(5, "two"));
    }

    @Test
    public void whenDelete() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "one");
        map.delete(1);
        assertThat(map.get(1), is(nullValue()));
    }

    @Test
    public void whenAddThenIt() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "one");
        map.iterator().next();
        SimpleHashMap.Node rsl = map.iterator().next();
        assertThat(rsl.value, is("one"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "one");
        Iterator<SimpleHashMap.Node<Integer, String>> it = map.iterator();
        map.insert(2, "two");
        it.next();
    }
}