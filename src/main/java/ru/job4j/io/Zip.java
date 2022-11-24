package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(
                        new ZipEntry(source.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(
                        new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(
                    new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validate(ArgsName argsName) {
        File file = new File(argsName.get("d"));
        if (!Files.isDirectory(file.toPath())) {
            throw new IllegalArgumentException(
                    String.format("Not directory %s", file.toPath()));
        }
        if (!argsName.get("e").startsWith(".")
                && argsName.get("e").length() <= 1) {
            throw new IllegalArgumentException(
                    "The argument has to be started with \".\""
            );
        }
        if (argsName.get("o").startsWith(".")
                && argsName.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException(
                    "Check out the name or format of file (has to be .zip)"
            );
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException(
                    "Number of array elements is not correct"
            );
        }
        if (!args[0].startsWith("-")) {
            throw new IllegalArgumentException(
                    "Arguments have to be started with dash \"-\" sign"
            );
        }
        Zip zip = new Zip();
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        Predicate<Path> condition =
                x -> !x.toFile()
                        .getName()
                        .endsWith(argsName.get("e"));
        Path root = Paths.get(argsName.get("d"));
        List<Path> sources = Search.search(root, condition);
        zip.packFiles(
                sources,
                new File(argsName.get("o"))
        );
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
    }
}