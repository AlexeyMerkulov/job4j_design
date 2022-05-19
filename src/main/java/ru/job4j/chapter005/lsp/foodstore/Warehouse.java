package ru.job4j.chapter005.lsp.foodstore;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {
    private List<Food> foodList = new ArrayList<>();

    private void add(Food food) {
        foodList.add(food);
    }

    @Override
    public List<Food> getFoodList() {
        return foodList;
    }

    @Override
    public void checkFood(Food food) {
        long daysSinceCreation = ChronoUnit.DAYS.between(food.getCreateDate(), LocalDate.now());
        long totalDays = ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
        if (daysSinceCreation < 0.25 * totalDays) {
            foodList.add(food);
        }
    }
}
