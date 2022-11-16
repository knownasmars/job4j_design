package ru.job4j.io;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class AnalysisTest {
    @Test
    void whenAnalyzeExampleOne() throws IOException {
        Analysis analysis = new Analysis();
        analysis.unavailable("source_file.csv", "target_file.csv");
        BufferedReader bf = new BufferedReader(
                new FileReader("target_file.csv"));
        assertThat(bf.readLine()).isEqualTo("10:57:01;10:59:01;");
        assertThat(bf.readLine()).isEqualTo("11:01:02;11:02:02;");
    }

    @Test
    void whenAnalyze() throws IOException {
        Analysis analysis = new Analysis();
        analysis.unavailable("source_file_2.csv", "target_file_2.csv");
        BufferedReader bf = new BufferedReader(
                new FileReader("target_file_2.csv"));
        assertThat(bf.readLine()).isEqualTo("10:57:01;11:02:02;");
    }
}