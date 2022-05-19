package ru.job4j.chapter005.lsp.foodstore;

import java.util.List;

public class ControlQuality {
    private List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void checkQuality(Food food) {
        for (Store store : stores) {
            store.checkFood(food);
        }
    }
}
