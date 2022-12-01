package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws IOException {
        try (Scanner scanner = new Scanner(
                new FileInputStream(argsName.get("path")))) {
            String line;
            String[] tmp;
            int[] indexes = getIndexArray(argsName);
            while (scanner.hasNext()) {
                StringBuilder sb = new StringBuilder();
                line = scanner.nextLine();
                tmp = line.split(";");
                for (int index : indexes) {
                    sb.append(";").append(tmp[index]);
                }
                if ("stdout".equals(argsName.get("out"))) {
                    System.out.println(sb.substring(1, sb.length()));
                } else {
                    File file = new File(argsName.get("out"));
                    try (BufferedWriter bw = new BufferedWriter(
                            new OutputStreamWriter(
                                    Files.newOutputStream(
                                            Path.of(file.getAbsolutePath()))))) {
                        bw.write(sb.substring(1, sb.length())
                                + System.lineSeparator());
                        while (scanner.hasNext()) {
                            sb = new StringBuilder();
                            line = scanner.nextLine();
                            tmp = line.split(";");
                            for (int index : indexes) {
                                sb.append(";").append(tmp[index]);
                            }
                            bw.write(sb.substring(1, sb.length())
                                    + System.lineSeparator());
                        }
                    }
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        validate(args, ArgsName.of(args));
        handle(ArgsName.of(args));
    }

    private static int[] getIndexArray(ArgsName argsName) throws IOException {
        try (Scanner scanner = new Scanner(
                new FileInputStream(argsName.get("path")))) {
            String[] array = scanner.nextLine().split(";");
            String[] filters = argsName.get("filter").split(",");
            int[] indexes = new int[filters.length];
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < filters.length; j++) {
                    if (array[i].equals(filters[j])) {
                        indexes[j] = Arrays.asList(array).indexOf(filters[j]);
                        break;
                    }
                }
            }
            return indexes;
        }
    }

    public static void validate(String[] args, ArgsName argsName) {
        if (args.length != 4) {
            throw new IllegalArgumentException(
                    "Length of the arguments set has to be equal 4"
            );
        }
        File file = new File(argsName.get("path"));
        if (!file.isFile()) {
            throw new IllegalArgumentException(
                    String.format("It's not a file %s", file)
            );
        }
        File outFile = new File(argsName.get("out"));
        if (!("stdout".equals(outFile.getName())
                || outFile.getName().endsWith(".csv"))) {
            throw new IllegalArgumentException(
                    "Third argument has to be equal \"stdout\" "
                            + "or ended with \".csv\""
            );
        }
    }
}