package ru.job4j.hometask.io;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

/**
 * 1. Программа для поиска файлов.
 * 2. Программа должна искать данные в заданном каталоге и подкаталогах.
 * 3. Имя файла может задаваться, целиком, по маске, по регулярному выражению(не обязательно).
 * 4. Программа должна запускаться с параметрами, например:  -d=c:/ -n=*.?xt -t=mask -o=log.txt
 * 5. Программа должна записывать результат в файл.
 * 6. В программе должна быть валидация ключей и подсказка.
 */

public class FileSeeker {
    public static void main(String[] args) throws IOException {
        validate(args);
        ArgsName values = ArgsName.of(args);
        Path root = Paths.get(values.get("d"));
        Predicate<Path> condition = null;
        if ("regex".equals(values.get("t"))) {
            condition = p -> p.toFile()
                    .getName()
                    .matches(values.get("n"));
        }
        if ("mask".equals(values.get("t"))) {
            String mask = values.get("n");
            String tmp = "";
            System.out.println(mask);
            if (mask.contains(".")) {
                tmp = mask.replace(".", "[.]");
            }
            if (mask.contains("*")) {
                tmp = mask.replace("*", ".*");
            }
            if (tmp.contains("?")) {
                tmp = tmp.replace("?", ".");
            }
            final String newRegex = tmp;
            System.out.println(newRegex);
            condition = p -> p.toFile()
                    .getName()
                    .matches(newRegex);
        }
        if ("name".equals(values.get("t"))) {
            condition = p -> p.toFile()
                    .getName()
                    .endsWith(values.get("n"));
        }
        write(values, searcher(root, condition));
    }

    public static List<String> searcher(Path root, Predicate<Path> condition) throws IOException {
        return Search.search(root, condition)
                .stream()
                .map(x -> x.toFile().getName())
                .toList();
    }

    public static void write(ArgsName values, List<String> listFiles) {
        try (BufferedWriter br = new BufferedWriter(
                new FileWriter(values.get("o")))) {
            for (String s : listFiles) {
                br.write(s + System.lineSeparator());
                System.out.println(s);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void validate(String[] args) {
        if (args[0].length() == 0) {
            throw new IllegalArgumentException(
                    "First argument cannot be empty");
        }
        if (args.length != 4) {
            throw new IllegalArgumentException(
                    "Number of arguments is not valid");
        }
        ArgsName values = ArgsName.of(args);
        File file = new File(values.get("d"));
        if (!Files.exists(file.toPath())) {
            throw new IllegalArgumentException(
                    String.format("Not exist %s", file.toPath()));
        }
        if (!Files.isDirectory(file.toPath())) {
            throw new IllegalArgumentException(
                    String.format("Not directory %s", file.toPath()));
        }
        if (args[1].length() <= 1) {
            throw new IllegalArgumentException(
                    "Second argument length has to be more than 1");
        }
        if (!args[3].endsWith(".txt")) {
            throw new IllegalArgumentException(
                    "The file must have \".txt\" format");
        }
        if (!"mask".equals(values.get("t"))
                && !"name".equals(values.get("t"))
                && !"regex".equals(values.get("t"))) {
            throw new IllegalArgumentException(
                    "Check out the input parameters. It might be \"name\", \"mask\" or \"regex\"");
        }
    }
}
