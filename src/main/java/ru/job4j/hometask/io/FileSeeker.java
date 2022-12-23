package ru.job4j.hometask.io;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

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
        Path root = Paths.get(values.get("-d"));
        List<String> listFiles =
                Search.search(root, p -> p.toFile().getName().endsWith(values.get("-n")))
                .stream()
                .map(x -> x.toFile().getName())
                .toList();
        write(args, listFiles);
    }

    public static void write(String[] args, List<String> listFiles) {
        ArgsName values = ArgsName.of(args);
        try (BufferedWriter br = new BufferedWriter(
                new FileWriter(values.get("-o")))) {
            listFiles.forEach(x -> {
                try {
                    br.write(x);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void validate(String[] args) {
//        if (args[0].length() == 0) {
//            throw new IllegalArgumentException(
//                    "First argument cannot be empty");
//        }
//        File file = new File(args[0]);
//        if (!Files.exists(file.toPath())) {
//            throw new IllegalArgumentException(
//                    String.format("Not exist %s", file.toPath()));
//        }
//        if (!Files.isDirectory(file.toPath())) {
//            throw new IllegalArgumentException(
//                    String.format("Not directory %s", file.toPath()));
//        }
//        if (args[1].length() <= 1) {
//            throw new IllegalArgumentException(
//                    "Second argument length has to be more than 1");
//        }
//        if (!args[1].startsWith(".")) {
//            throw new IllegalArgumentException(
//                    "Second argument has to be started with \".\"");
//        }
    }
}
