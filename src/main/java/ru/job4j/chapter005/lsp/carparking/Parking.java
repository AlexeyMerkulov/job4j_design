package ru.job4j.chapter005.lsp.carparking;

public interface Parking {
    boolean parkCar(Car car);

    boolean checkFreeSpace(Car car);
}
