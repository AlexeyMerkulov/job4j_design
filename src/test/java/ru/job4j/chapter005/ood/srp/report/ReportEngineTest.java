package ru.job4j.chapter005.ood.srp.report;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

import java.text.SimpleDateFormat;
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
        StringBuilder expect = new StringBuilder()
                .append("<html><body>")
                .append("<p>Name; Hired; Fired; Salary;</p><p>")
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append("</p></body></html>");
        assertThat(report.generate(em -> true), is(expect.toString()));
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
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(report.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenAccountantGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report report = new AccountantReport(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary without NDFL;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary() * AccountantReport.NDFL).append(";")
                .append(System.lineSeparator());
        assertThat(report.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenJSONGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new JSONReport(store);
        StringBuilder expect = new StringBuilder()
                .append("[{\"name\":\"" + worker.getName()).append("\",")
                .append("\"hired\":{\"year\":"
                        + worker.getHired().get(Calendar.YEAR)).append(",")
                .append("\"month\":"
                        + worker.getHired().get(Calendar.MONTH)).append(",")
                .append("\"dayOfMonth\":"
                        + worker.getHired().get(Calendar.DAY_OF_MONTH)).append(",")
                .append("\"hourOfDay\":"
                        + worker.getHired().get(Calendar.HOUR_OF_DAY)).append(",")
                .append("\"minute\":"
                        + worker.getHired().get(Calendar.MINUTE)).append(",")
                .append("\"second\":"
                        + worker.getHired().get(Calendar.SECOND)).append("},")
                .append("\"fired\":{\"year\":"
                        + worker.getFired().get(Calendar.YEAR)).append(",")
                .append("\"month\":"
                        + worker.getFired().get(Calendar.MONTH)).append(",")
                .append("\"dayOfMonth\":"
                        + worker.getFired().get(Calendar.DAY_OF_MONTH)).append(",")
                .append("\"hourOfDay\":"
                        + worker.getFired().get(Calendar.HOUR_OF_DAY)).append(",")
                .append("\"minute\":"
                        + worker.getFired().get(Calendar.MINUTE)).append(",")
                .append("\"second\":"
                        + worker.getFired().get(Calendar.SECOND)).append("},")
                .append("\"salary\":"
                        + worker.getSalary()).append("}]");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenXMLGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new XMLReport(store);
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n")
                .append("<employees>\n")
                .append("    <list>\n")
                .append("        <employee>\n")
                .append("            <fired>"
                        + formater.format(worker.getFired().getTime()) + "</fired>\n")
                .append("            <hired>"
                        + formater.format(worker.getHired().getTime()) + "</hired>\n")
                .append("            <name>" + worker.getName() + "</name>\n")
                .append("            <salary>" + worker.getSalary() + "</salary>\n")
                .append("        </employee>\n")
                .append("    </list>\n")
                .append("</employees>\n");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}