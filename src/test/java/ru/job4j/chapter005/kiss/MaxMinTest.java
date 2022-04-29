package ru.job4j.chapter005.kiss;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MaxMinTest {

    MaxMin obj = new MaxMin();

    @Test
    public void whenMax() {
        List<Integer> values = List.of(4, 1, 3, 2, 6);
        int max = obj.max(values, Comparator.naturalOrder());
        assertThat(max, is(6));
    }

    @Test
    public void whenMin() {
        List<Integer> values = List.of(4, 1, 3, 2, 6);
        int min = obj.min(values, Comparator.naturalOrder());
        assertThat(min, is(1));
    }

    @Test
    public void whenEquals() {
        List<Integer> values = List.of(2, 2, 2);
        int min = obj.min(values, Comparator.naturalOrder());
        assertThat(min, is(2));
    }

    @Test
    public void whenEmpty() {
        List<Integer> values = List.of();
        Integer min = obj.min(values, Comparator.naturalOrder());
        assertNull(min);
    }
}