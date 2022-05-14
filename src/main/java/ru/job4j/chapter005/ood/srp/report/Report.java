package ru.job4j.chapter005.ood.srp.report;

import java.util.function.Predicate;

public interface Report {
    String generate(Predicate<Employee> filter);
}
