package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (values.get(key) == null || values.get(key).equals("")) {
            throw new IllegalArgumentException(
                    "Input key is not found in map"
            );
        }
        return values.get(key);
    }

    private static void validate(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("The array of argument is empty");
        }
        for (String arg : args) {
            if (!arg.startsWith("-")) {
                throw new IllegalArgumentException(
                        "The string has to be started with dash \"-\" sign");
            }
            if (!arg.contains("=")) {
                throw new IllegalArgumentException(
                        "The string has no \"=\"");
            }
            String[] tmp = arg.substring(1).split("=", 2);
            if (tmp[0].equals("")) {
                throw new IllegalArgumentException(
                        "The key argument is null or empty"
                );
            }
            if (tmp[1] == null || tmp[1].equals("")) {
                throw new IllegalArgumentException(
                        "The value argument is null or empty"
                );
            }
        }
    }

    private void parse(String[] args) {
        validate(args);
        for (String arg : args) {
            String[] tmp = arg.substring(1).split("=", 2);
            values.put(tmp[0], tmp[1]);
        }
    }

    public static ArgsName of(String[] args) {
        validate(args);
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}