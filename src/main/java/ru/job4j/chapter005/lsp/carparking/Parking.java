package ru.job4j.chapter005.lsp.carparking;

public interface Parking {
    void parkCar(Car car);

    boolean checkFreeSpace(Car car);
}
