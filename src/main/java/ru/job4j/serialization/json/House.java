package ru.job4j.serialization.json;

import java.util.Arrays;

public class House {
    private final boolean inhabitedByPerson;
    private final int stage;
    private final String complexName;
    private final Car car;
    private final String[] inhabitants;

    public House(boolean inhabitedByPerson, int stage, String complexName, Car car, String[] inhabitants) {
        this.inhabitedByPerson = inhabitedByPerson;
        this.stage = stage;
        this.complexName = complexName;
        this.car = car;
        this.inhabitants = inhabitants;
    }

    @Override
    public String toString() {
        return "House{"
                + "inhabitedByPerson=" + inhabitedByPerson
                + ", stage=" + stage
                + ", complexName='" + complexName + '\''
                + ", car=" + car
                + ", inhabitants=" + Arrays.toString(inhabitants) + '}';
    }

    public boolean isInhabitedByPerson() {
        return inhabitedByPerson;
    }

    public int getStage() {
        return stage;
    }

    public String getComplexName() {
        return complexName;
    }

    public Car getCar() {
        return car;
    }

    public String[] getInhabitants() {
        return inhabitants;
    }
}
