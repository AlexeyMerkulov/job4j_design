package ru.job4j.chapter005.lsp.carparking;

import java.util.ArrayList;
import java.util.List;

public class CarParking implements Parking {
    private int passengerCarLots;

    private int truckLots;

    private List<Car> carsList;;

    public CarParking(int carLots, int truckLots) {
        this.passengerCarLots = carLots;
        this.truckLots = truckLots;
        carsList = new ArrayList<>(carLots + truckLots);
    }

    @Override
    public boolean parkCar(Car car) {
        boolean rsl = false;
        int carSize = car.getSize();
        boolean checkResult = checkFreeSpace(car);
        if (checkResult && carSize == PassengerCar.SIZE) {
            passengerCarLots--;
            carsList.add(car);
            rsl = true;
        } else if (checkResult && truckLots >= PassengerCar.SIZE) {
            truckLots--;
            carsList.add(car);
            rsl = true;
        } else if (checkResult) {
            passengerCarLots = passengerCarLots - carSize;
            carsList.add(car);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean checkFreeSpace(Car car) {
        boolean rsl = false;
        int carSize = car.getSize();
        if (carSize == PassengerCar.SIZE && passengerCarLots >= PassengerCar.SIZE) {
            rsl = true;
        } else if (carSize > PassengerCar.SIZE
                && (truckLots >= PassengerCar.SIZE || passengerCarLots - carSize >= 0)) {
            rsl = true;
        }
        return rsl;
    }
}
