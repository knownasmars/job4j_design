package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader bf = new BufferedReader(
                new FileReader(source));
                PrintWriter out = new PrintWriter(
                new FileOutputStream(target))) {
            String s; String start; String end;
            while ((s = bf.readLine()) != null) {
                if (s.startsWith("400") || s.startsWith("500")) {
                    out.print(s.substring(4) + ";");
                    s = bf.readLine();
                    start = "";
                }
                if (s.startsWith("400") || s.startsWith("500")) {
                    String tmp = bf.readLine();
                    out.println(tmp.substring(4, s.length()) + ";");
                    end = "";
                    continue;
                }
                if (end) {

                }
//                if (bf.readLine() == null) {
//                    out.println(s.substring(4) + ";");
//                }

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