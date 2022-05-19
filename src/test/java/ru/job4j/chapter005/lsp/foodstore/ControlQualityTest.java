package ru.job4j.chapter005.lsp.foodstore;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ControlQualityTest {

    @Test
    public void whenSortToWarehouse() {
        Food cheese = new Cheese("cheese", LocalDate.of(2022, 7, 30),
                LocalDate.of(2022, 5, 15), 300);
        Food chicken = new Chicken("chicken", LocalDate.of(2022, 8, 30),
                LocalDate.of(2022, 5, 1), 400);
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        List<Store> stores = new ArrayList<>();
        stores.add(warehouse);
        stores.add(shop);
        stores.add(trash);
        ControlQuality cq = new ControlQuality(stores);
        cq.checkQuality(cheese);
        cq.checkQuality(chicken);
        assertThat(warehouse.getFoodList(), is(List.of(cheese, chicken)));
        assertThat(shop.getFoodList(), is(List.of()));
        assertThat(trash.getFoodList(), is(List.of()));
    }

    @Test
    public void whenSortToShop() {
        Food milk = new Milk("milk", LocalDate.of(2022, 6, 30),
                LocalDate.of(2022, 4, 19), 90);
        Food chicken = new Chicken("chicken", LocalDate.of(2022, 5, 30),
                LocalDate.of(2022, 4, 1), 400);
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        List<Store> stores = new ArrayList<>();
        stores.add(warehouse);
        stores.add(shop);
        stores.add(trash);
        ControlQuality cq = new ControlQuality(stores);
        cq.checkQuality(milk);
        cq.checkQuality(chicken);
        assertThat(shop.getFoodList(), is(List.of(milk, chicken)));
        assertThat(chicken.getDiscount(), is(0.5));
        assertThat(warehouse.getFoodList(), is(List.of()));
        assertThat(trash.getFoodList(), is(List.of()));
    }

    @Test
    public void whenSortToTrash() {
        Food milk = new Milk("milk", LocalDate.of(2022, 5, 3),
                LocalDate.of(2022, 4, 19), 90);
        Food chicken = new Chicken("chicken", LocalDate.of(2022, 5, 15),
                LocalDate.of(2022, 4, 1), 400);
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        List<Store> stores = new ArrayList<>();
        stores.add(warehouse);
        stores.add(shop);
        stores.add(trash);
        ControlQuality cq = new ControlQuality(stores);
        cq.checkQuality(milk);
        cq.checkQuality(chicken);
        assertThat(trash.getFoodList(), is(List.of(milk, chicken)));
        assertThat(warehouse.getFoodList(), is(List.of()));
        assertThat(shop.getFoodList(), is(List.of()));
    }
}