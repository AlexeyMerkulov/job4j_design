package ru.job4j.chapter005.ood.srp.report;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class HRReport implements Report {

    private Store store;

    public HRReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;");
        text.append(System.lineSeparator());
        List<Employee> selectedEmp = store.findBy(filter);
        selectedEmp.sort((x, y) -> (int) (y.getSalary() - x.getSalary()));
        for (Employee employee : selectedEmp) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
