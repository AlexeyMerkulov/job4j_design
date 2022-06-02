package ru.job4j.chapter005.lsp.foodstore;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void checkQuality(Food food) {
        for (Store store : stores) {
            if (store.checkFood(food)) {
                store.add(food);
            }
        }
    }

    public void resort() {
        List<Food> resortedFood = new ArrayList<>();
        stores.forEach(x -> resortedFood.addAll(x.deleteAll()));
        resortedFood.forEach(this::checkQuality);
    }
}
