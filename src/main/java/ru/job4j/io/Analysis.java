package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader bf = new BufferedReader(
                new FileReader(source));
                PrintWriter out = new PrintWriter(
                new FileOutputStream(target))) {
            String s, start, end;
            while ((s = bf.readLine()) != null) {
                if (s.startsWith("400") || s.startsWith("500")) {
                    start = s.substring(4) + ";";
                    out.print(start);
                    while (s.startsWith("400") || s.startsWith("500")) {
                        s = bf.readLine();
                    }
                    end = s.substring(4) + ";";
                    out.println(end);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(
                new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}