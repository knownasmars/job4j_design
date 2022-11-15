package ru.job4j.io;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader text = new BufferedReader(
                new FileReader(this.path))) {
            String s;
            while ((s = text.readLine()) != null) {
                s = s.trim();
                if (s.startsWith("#") || s.equals("")) {
                    continue;
                }
                if (!s.contains("=")
                        || (s.startsWith("=") || s.endsWith("="))
                        || (s.startsWith("=") && s.endsWith("="))) {
                    throw new IllegalArgumentException();
                }
                String[] spl = s.split("=", 2);
                values.put(spl[0], spl[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
        Config c = new Config("app.properties");
        c.load();
    }
}