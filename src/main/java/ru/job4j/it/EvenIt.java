package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIt implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;

    public EvenIt(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean rsl = false;
        for (int i = point; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    @Override
    public Integer next() {
        Integer rsl = null;
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        for (int i = point; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                rsl = data[i];
                point = i + 1;
                break;
            }
        }
        return rsl;
    }
}
