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
        Food cheese = new Cheese("cheese", LocalDate.now().plusDays(25),
                LocalDate.now().minusDays(5), 300, 30);
        Food chicken = new Chicken("chicken", LocalDate.now().plusDays(60),
                LocalDate.now().minusDays(10), 400, 25);
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
        Food milk = new Milk("milk", LocalDate.now().plusDays(10),
                LocalDate.now().minusDays(10), 90, 15);
        Food chicken = new Chicken("chicken", LocalDate.now().plusDays(2),
                LocalDate.now().minusDays(10), 400, 35);
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
        assertThat(chicken.getPrice(), is(260.0));
        assertThat(warehouse.getFoodList(), is(List.of()));
        assertThat(trash.getFoodList(), is(List.of()));
    }

    @Test
    public void whenSortToTrash() {
        Food milk = new Milk("milk", LocalDate.now(),
                LocalDate.now().minusDays(20), 90, 25);
        Food chicken = new Chicken("chicken", LocalDate.now().minusDays(1),
                LocalDate.now().minusDays(30), 400, 25);
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

    @Test
    public void whenSortToDifferentStores() {
        Food milk = new Milk("milk", LocalDate.now().plusDays(100),
                LocalDate.now().minusDays(10), 90, 25);
        Food chicken = new Chicken("chicken", LocalDate.now().plusDays(20),
                LocalDate.now().minusDays(10), 400, 25);
        Food cheese = new Cheese("chicken", LocalDate.now().minusDays(1),
                LocalDate.now().minusDays(30), 600, 25);
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
        cq.checkQuality(cheese);
        assertThat(warehouse.getFoodList(), is(List.of(milk)));
        assertThat(shop.getFoodList(), is(List.of(chicken)));
        assertThat(trash.getFoodList(), is(List.of(cheese)));
    }
}