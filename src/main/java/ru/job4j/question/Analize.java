package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
        Map<Integer, String> map = new HashMap<>();
        for (User u : previous) {
            map.put(u.getId(), u.getName());
        }
        for (User u : current) {
            if (!map.containsKey(u.getId())) {
                info.setAdded(info.getAdded() + 1);
            }
            if (map.containsKey(u.getId())
                    && !Objects.equals(map.get(u.getId()), u.getName())) {
                info.setChanged(info.getChanged() + 1);
            }
            if (previous.contains(u) && !current.contains(u)) {
                info.setDeleted(info.getDeleted() + 1);
            }
        }
        if (previous.size() > current.size()) {
            for (User u : previous) {
                if (!current.contains(u)) {
                    info.setDeleted(info.getDeleted() + 1);
                }
            }
        }
        return info;
    }
}