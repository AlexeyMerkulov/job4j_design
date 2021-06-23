package ru.job4j.serialization.jsonobject;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.serialization.json.Engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public boolean isNew() {
        return isNew;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public Engine getEngine() {
        return engine;
    }

    public String[] getOptions() {
        return options;
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
        JSONObject jsonEngine = new JSONObject("{\"type\":\"2.5L\"}");
        List<String> list = new ArrayList<>();
        list.add("metallic paint");
        list.add("winter tires");
        JSONArray jsonOptions = new JSONArray(list);
        final Car car = new Car(true, 25000, "Mazda", new Engine("2.5L"),
                new String[] {"metallic paint", "winter tires"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isNew", car.isNew);
        jsonObject.put("price", car.getPrice());
        jsonObject.put("engine", jsonEngine);
        jsonObject.put("options", jsonOptions);
        System.out.println(jsonObject.toString());

        System.out.println(new JSONObject(car).toString());
    }

}