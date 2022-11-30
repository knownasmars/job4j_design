package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        File file = new File(argsName.get("out"));
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(file.getName()))) {
            Scanner scanner = new Scanner(
                    new FileInputStream(argsName.get("path")));
            String line;
            String[] tmp;
            int[] indexes = getIndexArray(argsName);
            while (scanner.hasNext()) {
                line = scanner.nextLine();
                tmp = line.split(";");
                StringBuilder sb = new StringBuilder();
                for (int index : indexes) {
                    sb.append(";").append(tmp[index]);
                }
                if (argsName.get("out").equals("stdout")) {
                    System.out.println(sb.substring(1, sb.length()));
                }
                else {
                    bw.write(sb.substring(1, sb.length())
                            + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        validate(args, ArgsName.of(args));
        handle(ArgsName.of(args));
    }

    private static int[] getIndexArray(ArgsName argsName) throws Exception {
        Scanner scanner = new Scanner(
                new FileInputStream(argsName.get("path")));
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

    public static void validate(String[] args, ArgsName argsName) {
        if (args.length == 0) {
            throw new IllegalArgumentException(
                    "The set of arguments cannot be empty");
        }
        File file = new File(argsName.get("path"));
        if (!file.isFile()) {
            throw new IllegalArgumentException(
                    String.format("It's not a file %s", file)
            );
        }
        File outFile = new File(argsName.get("out"));
        if (!(outFile.getName().equals("stdout") || Files.isDirectory(outFile.toPath()))) {
            throw new IllegalArgumentException(
                    "Third argument has to be equal \"stdout\" "
                            + "or contain the path where "
                            + "to write out the file"
            );
        }
    }
}