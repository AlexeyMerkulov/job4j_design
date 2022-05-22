package ru.job4j.chapter005.lsp.carparking;

import java.util.ArrayList;
import java.util.List;

public class CarParking implements Parking {
    private int passengerCarLots;

    private int truckLots;

    private List<Car> carsList = new ArrayList<>();

    public CarParking(int carLots, int truckLots) {
        this.passengerCarLots = carLots;
        this.truckLots = truckLots;
    }

    @Override
    public boolean parkCar(Car car) {
        return false;
    }

    @Override
    public boolean checkFreeSpace(Car car) {
        return false;
    }
}
