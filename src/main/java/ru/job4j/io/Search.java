package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void validate(String[] args) {
        if (args[0].length() == 0) {
            throw new IllegalArgumentException(
                    "First argument cannot be empty");
        }
        if (!args[0].contains("\\")) {
            throw new IllegalArgumentException(
                    "Incorrect catalog address.");
        }
        if (args[1].length() <= 1) {
            throw new IllegalArgumentException(
                    "Second argument length has to be more than 1");
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException(
                    "Second argument has to be started with \".\"");
        }
    }

    public static void main(String[] args) throws IOException {
        validate(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().startsWith(args[1]))
                .stream().map(x -> x.toFile().getName())
                .forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}