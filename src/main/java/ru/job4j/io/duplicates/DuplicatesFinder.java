package ru.job4j.io.duplicates;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor dv = new DuplicatesVisitor();
        Files.walkFileTree(Paths.get(
                "C:\\projects\\job4j_design\\task_data"), dv);
        dv.getInfo().forEach((key, value) -> {
            System.out.println(key);
            value.forEach(System.out::println);
        });
    }
}