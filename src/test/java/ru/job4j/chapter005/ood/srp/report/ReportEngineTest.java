package ru.job4j.chapter005.ood.srp.report;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import java.util.Calendar;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenProgrammerGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report report = new ProgrammerReport(store);
        String expect = "<html><body>"
                + "<p>Name; Hired; Fired; Salary;</p><p>"
                + worker.getName() + ";"
                + worker.getHired() + ";"
                + worker.getFired() + ";"
                + worker.getSalary() + ";"
                + "</p></body></html>";
        assertThat(report.generate(em -> true), is(expect));
    }

    @Test
    public void whenHRGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        store.add(worker1);
        Employee worker2 = new Employee("Sergey", now, now, 150);
        store.add(worker2);
        Report report = new HRReport(store);
        String expect = "Name; Salary;"
                + System.lineSeparator()
                + worker2.getName() + ";"
                + worker2.getSalary() + ";"
                + System.lineSeparator()
                + worker1.getName() + ";"
                + worker1.getSalary() + ";"
                + System.lineSeparator();
        assertThat(report.generate(em -> true), is(expect));
    }

    @Test
    public void whenAccountantGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report report = new AccountantReport(store);
        String expect = "Name; Hired; Fired; Salary without NDFL;"
                + System.lineSeparator()
                + worker.getName() + ";"
                + worker.getHired() + ";"
                + worker.getFired() + ";"
                + (worker.getSalary() * 0.87) + ";"
                + System.lineSeparator();
        assertThat(report.generate(em -> true), is(expect));
    }
}