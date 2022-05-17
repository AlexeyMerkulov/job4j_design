package ru.job4j.chapter005.ood.srp.report;

import java.util.List;
import java.util.function.Predicate;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;

public class XMLReport implements Report {

    private Store store;

    public XMLReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> selectedEmp = store.findBy(filter);
        JAXBContext context;
        Marshaller marshaller = null;
        try {
            context = JAXBContext.newInstance(Employees.class);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        String rsl = "";
        try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(new Employees(selectedEmp), writer);
                rsl = writer.getBuffer().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }
}

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
class Employees {

    @XmlElementWrapper(name = "list")
    @XmlElement(name = "employee")
    private List<Employee> list;

    public Employees() {
    }

    public Employees(List<Employee> list) {
        this.list = list;
    }
}
