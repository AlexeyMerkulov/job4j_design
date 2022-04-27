package ru.job4j.chapter001.set;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenAddDifferentElements() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.add(2));
        assertTrue(set.add(3));
        assertTrue(set.contains(2));
        assertFalse(set.add(3));
    }

    @Test
    public void whenAddNullAndNotNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertFalse(set.add(null));
        assertTrue(set.add(15));
        assertTrue(set.add(9));
        assertTrue(set.contains(9));
        assertFalse(set.add(15));
    }

    @Test
    public void whenIterate() {
        Set<Integer> set = new SimpleSet<>();
        set.add(null);
        set.add(10);
        Iterator<Integer> itr = set.iterator();
        itr.next();
        assertTrue(itr.hasNext());
        assertThat(itr.next(), is(10));
        assertFalse(itr.hasNext());
    }

}