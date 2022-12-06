package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "ability")
public class Abilities {

    @XmlAttribute
    private String ability;

    public Abilities() {

    }

    public Abilities(String ability) {
        this.ability = ability;
    }
}
