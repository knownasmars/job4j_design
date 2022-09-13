package ru.job4j.generics;

public class Animal<K, V> {
    private K paws;
    private V family;
    public Animal(K paws, V family) {
        this.family = family;
        this.paws = paws;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "paws=" + paws +
                ", family='" + family + '\'' +
                '}';
    }
}
