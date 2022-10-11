package ru.job4j.hash;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Map<User, Object> map = new HashMap<>(16);
        User u1 = new User("Артем", 0, Calendar.getInstance());
        int hashcode1 = u1.hashCode();
        int hash1 = hashcode1 ^ (hashcode1 >>> 16);
        int bucket1 = hash1 & 15;
        User u2 = new User("Артем", 0, Calendar.getInstance());
        int hashcode2 = u2.hashCode();
        int hash2 = hashcode2 ^ (hashcode2 >>> 16);
        int bucket2 = hash2 & 15;
        map.put(u1, new Object());
        map.put(u2, new Object());
        System.out.printf("u1 - hashcode: %s, hash: %s, bucket: %s " + System.lineSeparator(),
                hashcode1, hash1, bucket1);
        System.out.printf("u2 - hashcode: %s, hash: %s, bucket: %s" + System.lineSeparator(),
                hashcode2, hash2, bucket2);
        System.out.println(u1.equals(u2));
    }
}
