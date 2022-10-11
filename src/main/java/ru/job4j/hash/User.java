package ru.job4j.hash;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        Map<User, Object> map = new HashMap<>(16);
        User u1 = new User("Артем", 0, new GregorianCalendar(1992, Calendar.APRIL, 12));
        int hashcode1 = u1.hashCode();
        int hash1 = hashcode1 ^ (hashcode1 >>> 16);
        int bucket1 = hash1 & 15;
        User u2 = new User("Артем", 0, new GregorianCalendar(1992, Calendar.APRIL, 12));
        int hashcode2 = u2.hashCode();
        int hash2 = hashcode2 ^ (hashcode2 >>> 16);
        int bucket2 = hash2 & 15;
        map.put(u1, 12);
        map.put(u2, 13);
        System.out.printf("u1 - hashcode: %s, hash: %s, bucket: %s " + System.lineSeparator(),
                hashcode1, hash1, bucket1);
        System.out.printf("u2 - hashcode: %s, hash: %s, bucket: %s" + System.lineSeparator(),
                hashcode2, hash2, bucket2);
        System.out.println(u1.equals(u2));
    }
}
