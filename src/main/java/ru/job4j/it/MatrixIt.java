package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        int rsl = 0;
        if (row == 0 && column == 0) {
            for (int i = 0; i < data.length; i++) {
                if (data[i].length == 0) {
                    rsl++;
                }
            }
        }
        return row < data.length && rsl < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        while (data[row].length == 0) {
            row++;
        }
        Integer rsl = data[row][column++];
        if (column == data[row].length) {
            column = 0;
            row++;
        }
        return rsl;
    }
}