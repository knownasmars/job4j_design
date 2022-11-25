package ru.job4j.io;

import com.sun.source.tree.ContinueTree;

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
            List<String> answers = readPhrases();
            String input = br.readLine();
            while (!Objects.equals(input, OUT)) {
                log.add(input);
                System.out.println(input);
//                if (!Objects.equals(input, STOP)) {
//                    String tmp = getRandom(answers);
//                    log.add(tmp);
//                    System.out.println(tmp);
//                }
//                while (input.equals(STOP) && !input.equals(CONTINUE)) {
//                    input = br.readLine();
//                    log.add(input);
//                    System.out.println(input);
//                }
//                if (Objects.equals(input, CONTINUE)) {
//                    log.add(input);
//                    System.out.println(input);
//                }
                saveLog(log);
                input = br.readLine();
            }
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
        List<String> chat = cc.readPhrases();
        cc.saveLog(chat);
    }
}
