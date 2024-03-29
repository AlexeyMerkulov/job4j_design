package ru.job4j.chapter005.ood.srp.report;

import java.util.function.Predicate;

public class ProgrammerReport implements Report {

    private Store store;

    public ProgrammerReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<html><body>")
                .append("<p>Name; Hired; Fired; Salary;</p>");
        for (Employee employee : store.findBy(filter)) {
            text.append("<p>")
                    .append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append("</p>");
        }
        text.append("</body></html>");
        return text.toString();
    }
}
