package ru.job4j.io;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class AnalysisTest {
    @Test
    void whenAnalyze() {
        Analysis analysis = new Analysis();
        analysis.unavailable(
                "source_file.csv", "target_file.csv");
        try (BufferedReader bf = new BufferedReader(
                new FileReader("target_file.csv"))) {
            String s = bf.readLine();
            System.out.println(s);
            System.out.println(bf.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}