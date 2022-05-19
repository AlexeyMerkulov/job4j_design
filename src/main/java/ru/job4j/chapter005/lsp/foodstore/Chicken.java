package ru.job4j.chapter005.lsp.foodstore;

import java.time.LocalDate;

public class Chicken extends Food {
    public Chicken(String name, LocalDate expiryDate, LocalDate createDate, double price) {
        super(name, expiryDate, createDate, price);
    }
}
