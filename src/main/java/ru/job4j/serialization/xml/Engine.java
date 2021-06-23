package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "engine")
public class Engine {

    @XmlAttribute
    private String type;

    public Engine() {
    }

    public Engine(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "type='" + type + '\''
                + '}';
    }
}