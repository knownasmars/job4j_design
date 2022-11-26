package ru.job4j.io;

import java.io.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public static String getRandom(List<String> list) {
        var random = new SecureRandom();
        return list.get(random.nextInt(list.size()));
    }

    public void run() {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in))) {
            List<String> log = new ArrayList<>();
            String input = br.readLine();
            List<String> phrases = readPhrases();
            while (!Objects.equals(input, OUT)) {
                String tmp = getRandom(phrases);
                log.add(input);
                log.add(tmp);
                System.out.println(input);
                System.out.println(tmp);
                saveLog(log);
                input = br.readLine();
                if (Objects.equals(input, STOP)) {
                    log.add(input);
                    System.out.println(input);
                    input = br.readLine();
                    saveLog(log);
                    while (!Objects.equals(input, CONTINUE)) {
                        log.add(input);
                        System.out.println(input);
                        input = br.readLine();
                        saveLog(log);
                    }
                }
            }
            log.add(input);
            System.out.println(input);
            saveLog(log);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new FileReader(botAnswers))) {
            String input = br.readLine();
            while (input != null) {
                list.add(input);
                input = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void saveLog(List<String> log) {
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(path))) {
            log.forEach(x -> {
                try {
                    bw.write(x + System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat(
                "dialog.txt", "answers.txt");
        cc.run();
    }
}
