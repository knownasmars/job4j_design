package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Person person = new Person(false, 30, new Contact("11-111"),
                new String[] {"Worker", "Married"});
        final Person womanPerson = new Person(true, 25, new Contact("22-222"),
                new String[] {"Cooker", "Married"});
        final Car car = new Car("Lambo");
        final String[] inhabitants = new String[]{"person1", "person2"};
        final House house = new House(true, 2, "ZK Javist",
                car, inhabitants);
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(house));
        final String houseJson  =
                "{"
                        + "\"inhabitedByPerson\":true,"
                        + "\"stage\":15,"
                        + "\"car\":"
                        + "{"
                        + "\"brand\": \"Ferrari\""
                        + "},"
                        + "\"inhabitants\":"
                        + "[\"person3\", \"person4\"]"
                        + "}";

        final House houseMod = gson.fromJson(houseJson, House.class);
        System.out.println(houseMod);
    }
}