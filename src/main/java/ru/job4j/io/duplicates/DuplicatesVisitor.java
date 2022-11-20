package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, List<Path>> map = new HashMap<>();

    public void findDuplicate() {
        for (Map.Entry<FileProperty, List<Path>> entry : map.entrySet()) {
            if (entry.getValue().size() < 2) {
                map.remove(entry.getKey());
            }
        }
    }

    public void printDuplicates() {
        map.forEach((key, value) -> {
            System.out.println(key);
            value.forEach(System.out::println);
        });
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fp = new FileProperty(
                file.toFile().length(),
                file.toFile().getName()
        );
        if (map.containsKey(fp)) {
            map.get(fp).add(file.toAbsolutePath());
        } else {
            map.put(fp, new ArrayList<>());
            map.get(fp).add(file.toAbsolutePath());
        }
        return FileVisitResult.CONTINUE;
    }
}