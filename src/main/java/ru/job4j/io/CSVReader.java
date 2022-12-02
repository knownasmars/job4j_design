package ru.job4j.io;

import java.io.*;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws IOException {
        String target = argsName.get("out");
        try (Scanner scanner = new Scanner(
                new File(argsName.get("path")));
             PrintStream out =
                     "stdout".equals(target)
                             ? System.out : new PrintStream(target)) {
            String[] tmp; String line;
            int[] indexes = getIndexArray(argsName);
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNext()) {
                line = scanner.nextLine();
                tmp = line.split(argsName.get("delimiter"));
                for (int index : indexes) {
                    sb.append(tmp[index]).append(argsName.get("delimiter"));
                }
                out.println(sb);
                sb.delete(0, sb.length());
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
        String outFile = argsName.get("out");
        if (!("stdout".equals(outFile)
                || outFile.endsWith(".csv"))) {
            throw new IllegalArgumentException(
                    "Third argument has to be equal \"stdout\" "
                            + "or ended with \".csv\""
            );
        }
    }
}