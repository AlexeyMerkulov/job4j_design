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
        boolean rsl = false;
        int carSize = car.getSize();
        if (checkFreeSpace(car)) {
            if (carSize == PassengerCar.SIZE) {
                passengerCarLots--;
            } else if (truckLots >= 1) {
                truckLots--;
            } else {
                passengerCarLots = passengerCarLots - carSize;
            }
            carsList.add(car);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean checkFreeSpace(Car car) {
        boolean rsl = false;
        int carSize = car.getSize();
        if (carSize == PassengerCar.SIZE) {
            if (passengerCarLots >= 1) {
                rsl = true;
            }
        } else {
            if (truckLots >= 1 || passengerCarLots - carSize >= 0) {
                rsl = true;
            }
        }
        return rsl;
    }
}
