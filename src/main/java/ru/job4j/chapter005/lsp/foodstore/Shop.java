package ru.job4j.chapter005.lsp.foodstore;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {
    private List<Food> foodList = new ArrayList<>();

    public boolean add(Food food) {
        boolean rsl = false;
        if (checkFood(food)) {
            if (getPercentLifeExpired(food) > 0.75) {
                food.setPrice((100 - food.getDiscount()) * 0.01 * food.getPrice());
            }
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
        double percentLife = getPercentLifeExpired(food);
        return percentLife >= 0.25 && percentLife < 1;
    }
}
