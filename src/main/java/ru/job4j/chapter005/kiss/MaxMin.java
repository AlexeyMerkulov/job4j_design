package ru.job4j.chapter005.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        Predicate<Integer> predicate = x -> x < 0;
        return util(value, comparator, predicate);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        Predicate<Integer> predicate = x -> x > 0;
        return util(value, comparator, predicate);
    }

    private <T> T util(List<T> value, Comparator<T> comparator, Predicate<Integer> predicate) {
        if (value.size() == 0) {
            return null;
        }
        T rsl = value.get(0);
        for (T el : value) {
            if (predicate.test(comparator.compare(rsl, el))) {
                rsl = el;
            }
        }
        return rsl;
    }
}