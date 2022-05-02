package ru.job4j.chapter005.template;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class GeneratorTest {

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenNoSuchKey() {
        Generator generator = new WordGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = Map.of("key1", "value1");
        generator.produce(template, map);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenExtraKeys() {
        Generator generator = new WordGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = Map.of("name", "Alexey", "subject", "you", "key1", "value1");
        generator.produce(template, map);
    }

    @Ignore
    @Test
    public void whenSuccessGenerate() {
        Generator generator = new WordGenerator();
        String template = "I am an ${name}, Who are ${subject}?";
        Map<String, String> map = Map.of("name", "Alexey", "subject", "you");
        String result = "I am an Alexey, Who are you?";
        assertThat(result, is(generator.produce(template, map)));
    }
}