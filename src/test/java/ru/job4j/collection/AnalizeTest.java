package ru.job4j.collection;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.List;

public class AnalizeTest {

    @Test
    public void whenOneElChanged() {
        List<Analize.User> previous = new ArrayList<>();
        previous.add(new Analize.User(1, "Andrey"));
        previous.add(new Analize.User(2, "Boris"));
        previous.add(new Analize.User(3, "Gleb"));
        previous.add(new Analize.User(4, "Dmitriy"));
        previous.add(new Analize.User(5, "Nikita"));
        List<Analize.User> current = List.of(new Analize.User(1, "Andrey")
                , new Analize.User(2, "Evgeniy")
                , new Analize.User(3, "Gleb"));
        Analize analize = new Analize();
        Analize.Info info = analize.diff(previous, current);
        assertThat(info.changed, is(1));
        assertThat(info.deleted, is(2));
        assertThat(info.added, is(0));
    }

    @Test
    public void whenAllElementsDeleted() {
        List<Analize.User> previous = new ArrayList<>();
        previous.add(new Analize.User(1, "Andrey"));
        previous.add(new Analize.User(2, "Boris"));
        previous.add(new Analize.User(3, "Gleb"));
        previous.add(new Analize.User(4, "Dmitriy"));
        previous.add(new Analize.User(5, "Nikita"));
        List<Analize.User> current = List.of();
        Analize analize = new Analize();
        Analize.Info info = analize.diff(previous, current);
        assertThat(info.deleted, is(5));
        assertThat(info.added, is(0));
        assertThat(info.changed, is(0));
    }

    @Test
    public void whenAllElementsAdded() {
        List<Analize.User> previous = new ArrayList<>();
        previous.add(new Analize.User(1, "Andrey"));
        previous.add(new Analize.User(2, "Boris"));
        previous.add(new Analize.User(3, "Gleb"));
        previous.add(new Analize.User(4, "Dmitriy"));
        previous.add(new Analize.User(5, "Nikita"));
        List<Analize.User> current = List.of(new Analize.User(7, "Andrey")
                , new Analize.User(8, "Boris")
                , new Analize.User(9, "Gleb"));
        Analize analize = new Analize();
        Analize.Info info = analize.diff(previous, current);
        assertThat(info.added, is(3));
        assertThat(info.changed, is(0));
        assertThat(info.deleted, is(5));
    }
}