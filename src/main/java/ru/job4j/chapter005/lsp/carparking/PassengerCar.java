package ru.job4j.chapter005.lsp.carparking;

public class PassengerCar implements Car {
    private final int size = 1;

    @Override
    public int getSize() {
        return size;
    }
}
