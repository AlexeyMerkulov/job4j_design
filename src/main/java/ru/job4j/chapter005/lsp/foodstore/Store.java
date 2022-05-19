package ru.job4j.chapter005.lsp.foodstore;

import java.util.List;

public interface Store {

    List<Food> getFoodList();

    void checkFood(Food food);
}
