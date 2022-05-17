package ru.job4j.chapter005.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.function.Predicate;

public class JSONReport implements Report {

    private Store store;

    public JSONReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> selectedEmp = store.findBy(filter);
        Gson gson = new GsonBuilder().create();
        return gson.toJson(selectedEmp);
    }
}
