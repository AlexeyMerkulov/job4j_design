package ru.job4j.chapter005.lsp.foodstore;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {
    private List<Food> foodList = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (checkFood(food)) {
            foodList.add(food);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public List<Food> getFoodList() {
        return List.copyOf(foodList);
    }

    @Override
    public List<Food> deleteAll() {
        List<Food> rsl = getFoodList();
        foodList.clear();
        return rsl;
    }

    @Override
    public boolean checkFood(Food food) {
        return getPercentLifeExpired(food) < 0.25;
    }
}
