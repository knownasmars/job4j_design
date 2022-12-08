package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JSONObject jsonCar = new JSONObject("{\"brand\":\"Ferrari\"}");
        List<String> list = new ArrayList<>();
        list.add("person1");
        list.add("person2");
        JSONArray jsonInhabitants = new JSONArray(list);
        final House house = new House(true, 2, "ZK Javist",
                new Car("Lambo"), new String[]{"Alex", "Marry"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("inhabitedByPerson", house.isInhabitedByPerson());
        jsonObject.put("stage", house.getStage());
        jsonObject.put("complexName", house.getComplexName());
        jsonObject.put("car", jsonCar);
        jsonObject.put("inhabitants", jsonInhabitants);
        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(house).toString());
    }
}