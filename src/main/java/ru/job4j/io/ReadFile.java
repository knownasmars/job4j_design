package ru.job4j.io;

import java.io.*;

public class ReadFile {
    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new FileReader("even.txt"))) {
            in.lines().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}