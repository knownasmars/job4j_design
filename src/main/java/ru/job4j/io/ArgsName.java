package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (values.get(key) == null || values.get(key).equals("")) {
            throw new IllegalArgumentException(
                    "Input argument is null or empty"
            );
        }
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(
                    "No such argument"
            );
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String arg : args) {
            if (!arg.contains("=")) {
                throw new IllegalArgumentException(
                        "The string has no \"=\"");
            }
            if (!arg.contains("-")) {
                throw new IllegalArgumentException(
                        "The string has no dash sign \"-\"");
            }
            String[] tmp = arg.substring(1).split("=", 2);
            values.put(tmp[0], tmp[1]);
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
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