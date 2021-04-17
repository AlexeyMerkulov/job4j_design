package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean rsl = false;
        T check = findById(id);
        if (check != null) {
            int index = mem.indexOf(check);
            mem.set(index, model);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean delete(String id) {
        boolean rsl = false;
        T element = findById(id);
        if (element != null) {
            rsl = mem.remove(element);
        }
        return rsl;
    }

    @Override
    public T findById(String id) {
        T rsl = null;
        for (T element : mem) {
            if (element.getId().equals(id)) {
                rsl = element;
                break;
            }
        }
        return rsl;
    }
}