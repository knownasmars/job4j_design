package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader bf = new BufferedReader(
                new FileReader(source));
                PrintWriter out = new PrintWriter(
                new FileOutputStream(target))) {
            String s;
            while ((s = bf.readLine()) != null) {
                if (s.startsWith("400") || s.startsWith("500")) {
                    out.append(s, 4, s.length())
                            .append(";");
                    while (s.startsWith("400") || s.startsWith("500")) {
                        s = bf.readLine();
                    }
                    out.append(s, 4, s.length())
                            .append(";")
                            .append(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable(
                "source_file.csv", "target_file_3.csv");
        analysis.unavailable(
                "source_file_2.csv", "target_file_4.csv");
    }
}