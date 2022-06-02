package ru.job4j.chapter005.lsp.foodstore;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public interface Store {

    boolean add(Food food);

    List<Food> getFoodList();

    boolean checkFood(Food food);

    List<Food> deleteAll();

    default double getPercentLifeExpired(Food food) {
        long daysSinceCreation = ChronoUnit.DAYS.between(food.getCreateDate(), LocalDate.now());
        long totalDays = ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
        return daysSinceCreation * 1.0 / totalDays;
    }
}
