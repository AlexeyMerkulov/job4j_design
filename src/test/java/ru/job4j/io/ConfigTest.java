package ru.job4j.io;

import org.junit.Test;
import static org.hamcrest.Matchers.is;

import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr"));
        assertThat(config.value("surname"), is("Arsentev"));
    }

    @Test
    public void whenPairWithCommentsAndEmptyLines() {
        String path = "./data/pair_with_comments.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Alexey"));
        assertThat(config.value("surname"), is("Petrov"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenExceptionIsThrown() {
        String path = "./data/when_throw_exception.properties";
        Config config = new Config(path);
        config.load();
    }
}