package ru.job4j.chapter001.collection.it;

import org.hamcrest.core.Is;
import org.junit.Test;
import ru.job4j.chapter001.collection.it.ListUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(Arrays.asList(0, 1, 2, 3), Is.is(input));
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 5, 2);
    }

    @Test
    public void whenRemoveElement() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ListUtils.removeIf(input, x -> x % 2 == 0);
        assertThat(Arrays.asList(1, 3), Is.is(input));
    }

    @Test
    public void whenReplaceElement() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 9));
        ListUtils.replaceIf(input, x -> x % 3 == 0, 0);
        assertThat(Arrays.asList(1, 2, 0, 0), Is.is(input));
    }

    @Test
    public void whenRemoveElements() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 4, 9, 9));
        List<Integer> elements = new ArrayList<>(List.of(3, 4, 9));
        ListUtils.removeAll(input, elements);
        assertThat(Arrays.asList(1, 2), Is.is(input));
    }

}