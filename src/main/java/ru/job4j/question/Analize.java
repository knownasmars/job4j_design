package ru.job4j.question;

import java.util.*;
import java.util.stream.Collectors;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
        Map<Integer, String> map =
                current.stream()
                        .collect(Collectors.toMap(User::getId, User::getName));
        for (User u : previous) {
            if (!map.containsKey(u.getId())) {
                info.setDeleted(info.getDeleted() + 1);
            }
            if (map.containsKey(u.getId())
                    && !Objects.equals(map.get(u.getId()), u.getName())) {
                info.setChanged(info.getChanged() + 1);
            }
        }
        int tmpSize = current.size() - previous.size() + info.getDeleted();
        info.setAdded(info.getAdded() + tmpSize);
        return info;
    }
}