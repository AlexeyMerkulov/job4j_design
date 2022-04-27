package ru.job4j.chapter001.collection.it;

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
        for (int i = row; i < data.length; i++) {
            if (data[i].length == 0) {
                row++;
            } else {
                break;
            }
        }
        return row < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Integer rsl = data[row][column];
        if (column == data[row].length - 1) {
            column = 0;
            row++;
        } else {
            column++;
        }
        return rsl;
    }
}