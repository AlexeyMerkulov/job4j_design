package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {

    @XmlAttribute
    private boolean isNew;

    @XmlAttribute
    private int price;

    @XmlAttribute
    private String name;

    private Engine engine;

    @XmlElementWrapper(name = "options")
    @XmlElement(name = "option")
    private String[] options;

    public Car() {
    }

    public Car(boolean isNew, int price, String name, Engine engine, String[] options) {
        this.isNew = isNew;
        this.price = price;
        this.name = name;
        this.engine = engine;
        this.options = options;
    }

    @Override
    public String toString() {
        return "Car{"
                + "isNew=" + isNew
                + ", price=" + price
                + ", name='" + name + '\''
                + ", engine=" + engine
                + ", options=" + Arrays.toString(options)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {
        final Car car = new Car(true, 25000, "Mazda", new Engine("2.5L"),
                new String[] {"metallic paint", "winter tires"});
        JAXBContext context = JAXBContext.newInstance(Car.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(car, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Car result = (Car) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}