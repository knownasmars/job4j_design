package ru.job4j.serialization.xml;

public class Hero {
    private final boolean protogonism;
    private final int rating;
    private final String name;
    private final Abilities abilities;
    private final String[] enemies;

    public Hero(boolean protogonism, int rating, String name,
                Abilities abilities, String[] enemies) {
        this.protogonism = protogonism;
        this.rating = rating;
        this.name = name;
        this.abilities = abilities;
        this.enemies = enemies;
    }
}
