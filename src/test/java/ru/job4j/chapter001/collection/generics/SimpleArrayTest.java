package ru.job4j.chapter001.collection.generics;

import org.junit.Test;
import ru.job4j.chapter001.collection.generics.SimpleArray;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {

    @Test
    public void whenAddTwoElements() {
        SimpleArray<String> elements = new SimpleArray<>(2);
        elements.add("String1");
        elements.add("String2");
        assertThat(elements.get(0), is("String1"));
    }

    @Test
    public void whenSetNewElement() {
        SimpleArray<String> elements = new SimpleArray<>(2);
        elements.add("String1");
        elements.add("String2");
        elements.set(1, "String3");
        assertThat(elements.get(1), is("String3"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenIndexException() {
        SimpleArray<String> elements = new SimpleArray<>(2);
        elements.add("String1");
        elements.add("String2");
        elements.set(2, "String3");
    }

    @Test
    public void whenRemoveElement() {
        SimpleArray<String> elements = new SimpleArray<>(2);
        elements.add("String1");
        elements.add("String2");
        elements.remove(0);
        assertThat(elements.get(0), is("String2"));
    }

    @Test
    public void whenGetElement() {
        SimpleArray<String> elements = new SimpleArray<>(3);
        elements.add("String1");
        elements.add("String2");
        elements.add("String3");
        assertThat(elements.get(2), is("String3"));
    }

    @Test
    public void whenIterateElements() {
        SimpleArray<String> elements = new SimpleArray<>(3);
        elements.add("String1");
        elements.add("String2");
        elements.add("String3");
        Iterator itr = elements.iterator();
        itr.next();
        assertThat(itr.next(), is("String2"));
    }
}