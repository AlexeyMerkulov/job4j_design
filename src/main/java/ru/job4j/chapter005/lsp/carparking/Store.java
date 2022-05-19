package ru.job4j.chapter005.lsp.carparking;

import java.util.List;

public interface Store {
    void add(Car car);

    List<Car> findAll();
}
