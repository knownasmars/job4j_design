package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "hero")
@XmlAccessorType(XmlAccessType.FIELD)
public class Hero {

    @XmlAttribute
    private boolean protogonism;

    @XmlAttribute
    private int rating;

    @XmlAttribute
    private String name;

    @XmlElement
    private Abilities abilities;

    @XmlElementWrapper(name = "enemies")
    @XmlElement(name = "enemy")
    private String[] enemies;

    public Hero() {

    }

    public Hero(boolean protogonism, int rating, String name,
                Abilities abilities, String[] enemies) {
        this.protogonism = protogonism;
        this.rating = rating;
        this.name = name;
        this.abilities = abilities;
        this.enemies = enemies;
    }
}
