package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<FileProperty, List<Path>> duplicates = new HashMap<>();
    Map<FileProperty, List<Path>> map = new HashMap<>();

    public Map<FileProperty, List<Path>> getInfo() {
        return duplicates;
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
            map.put(
                    fp,
                    new ArrayList<>());
            map.get(fp).add(file.toAbsolutePath());
        }
        for (Map.Entry<FileProperty, List<Path>> entry : map.entrySet()) {
            if (entry.getValue().size() > 1) {
                duplicates.put(entry.getKey(), entry.getValue());
            }
        }
        return FileVisitResult.CONTINUE;
    }
}