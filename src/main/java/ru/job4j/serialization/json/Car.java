package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Car {
    private final boolean isNew;
    private final int price;
    private final String name;
    private final Engine engine;
    private final String[] options;

    public Car(boolean isNew, int price, String name, Engine engine, String[] options) {
        this.isNew = isNew;
        this.price = price;
        this.name = name;
        this.engine = engine;
        this.options = options;
    }

    @Override
    public String toString() {
        return "Car{"
                + "isNew=" + isNew
                + ", price=" + price
                + ", name='" + name + '\''
                + ", engine=" + engine
                + ", options=" + Arrays.toString(options)
                + '}';
    }

    public static void main(String[] args) {
        final Car car = new Car(true, 25000, "Mazda", new Engine("2.5L"),
                new String[] {"metallic paint", "winter tires"});
        final Gson gson = new GsonBuilder().create();
        String result = gson.toJson(car);
        System.out.println(result);
        final Car toCar = gson.fromJson(result, Car.class);
        System.out.println(toCar);
    }
}
