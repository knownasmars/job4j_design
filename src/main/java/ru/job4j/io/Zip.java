package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(target)))) {
            for (File source : sources) {
                zip.putNextEntry(
                        new ZipEntry(source.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(
                        new FileInputStream(source))) {
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

    public static void main(String[] args) throws IOException {
        Search.validate(args);
        String d = "-d=c:\\projects\\job4j_design\\";
        args[0] = d;
        String e = "-e=.class";
        args[1] = e;
        String o = "-o=project.zip";
        args[2] = o;
        ArgsName key = ArgsName.of(args);
        Predicate<Path> condition = x -> !x.endsWith(key.get("e"));
        Path root = Paths.get(key.get("d"));
        List<File> sources = Search.search(root, condition)
                .stream()
                .map(Path::toFile)
                .toList();
        Zip zip = new Zip();
        zip.packFiles(
                sources,
                new File(key.get("o"))
        );
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
    }
}