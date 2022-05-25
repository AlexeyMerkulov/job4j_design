package ru.job4j.chapter005.lsp.carparking;

import org.junit.Test;

import static org.junit.Assert.*;

public class CarParkingTest {

    @Test
    public void whenFreeLotsLeft() {
        Car passengerCar1 = new PassengerCar();
        Car passengerCar2 = new PassengerCar();
        Car passengerCar3 = new PassengerCar();
        Car truck1 = new Truck(2);
        Car truck2 = new Truck(3);
        Parking parking = new CarParking(5, 3);
        assertTrue(parking.parkCar(passengerCar1));
        assertTrue(parking.parkCar(passengerCar2));
        assertTrue(parking.parkCar(passengerCar3));
        assertTrue(parking.parkCar(truck1));
        assertTrue(parking.parkCar(truck2));
    }

    @Test
    public void whenNoFreeLotsForPassengerCars() {
        Car passengerCar1 = new PassengerCar();
        Car passengerCar2 = new PassengerCar();
        Car passengerCar3 = new PassengerCar();
        Car truck1 = new Truck(2);
        Car truck2 = new Truck(3);
        Parking parking = new CarParking(2, 3);
        assertTrue(parking.parkCar(passengerCar1));
        assertTrue(parking.parkCar(passengerCar2));
        assertFalse(parking.parkCar(passengerCar3));
        assertTrue(parking.parkCar(truck1));
        assertTrue(parking.parkCar(truck2));
    }

    @Test
    public void whenNoFreeLotsForTrucks() {
        Car passengerCar1 = new PassengerCar();
        Car passengerCar2 = new PassengerCar();
        Car passengerCar3 = new PassengerCar();
        Car truck1 = new Truck(2);
        Car truck2 = new Truck(3);
        Parking parking = new CarParking(7, 1);
        assertTrue(parking.parkCar(passengerCar1));
        assertTrue(parking.parkCar(passengerCar2));
        assertTrue(parking.parkCar(passengerCar3));
        assertTrue(parking.parkCar(truck1));
        assertTrue(parking.parkCar(truck2));
    }

    @Test
    public void whenNoFreeLotsAtAll() {
        Car passengerCar1 = new PassengerCar();
        Car passengerCar2 = new PassengerCar();
        Car passengerCar3 = new PassengerCar();
        Car truck1 = new Truck(2);
        Car truck2 = new Truck(3);
        Parking parking = new CarParking(4, 1);
        assertTrue(parking.parkCar(passengerCar1));
        assertTrue(parking.parkCar(passengerCar2));
        assertTrue(parking.parkCar(passengerCar3));
        assertTrue(parking.parkCar(truck1));
        assertFalse(parking.parkCar(truck2));
    }
}