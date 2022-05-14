package ru.job4j.chapter005.ood.srp.report;

import java.util.function.Predicate;

public class AccountantReport implements Report {

    private Store store;

    public AccountantReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary without NDFL;");
        text.append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary() * 0.87).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
