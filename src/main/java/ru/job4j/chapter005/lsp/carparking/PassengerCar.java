package ru.job4j.chapter005.lsp.carparking;

public class PassengerCar implements Car {
    public static final int SIZE = 1;

    @Override
    public int getSize() {
        return SIZE;
    }
}
