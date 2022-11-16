package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class AnalysisTest {
    @Test
    void whenAnalyzeExampleOne(@TempDir Path tempDir) throws IOException {
        Analysis analysis = new Analysis();
        File source = tempDir
                .resolve("source_file.csv").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("""
                    200 10:56:01
                    500 10:57:01
                    400 10:58:01
                    300 10:59:01
                    500 11:01:02
                    200 11:02:02"""
            );
        }
        File target = tempDir
                .resolve("target_file.csv").toFile();
        analysis.unavailable(
                source.getAbsolutePath(), target.getAbsolutePath()
        );
        try (BufferedReader bf = new BufferedReader(
                new FileReader(target))) {
            assertThat(bf.readLine())
                    .isEqualTo("10:57:01;10:59:01;");
            assertThat(bf.readLine())
                    .isEqualTo("11:01:02;11:02:02;");
        }
    }

    @Test
    void whenAnalyze(@TempDir Path tempDir) throws IOException {
        Analysis analysis = new Analysis();
        File source = tempDir
                .resolve("source_file_2.csv").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("""
                    200 10:56:01
                    500 10:57:01
                    400 10:58:01
                    500 10:59:01
                    400 11:01:02
                    300 11:02:02""");
        }
        File target = tempDir
                .resolve("target_file_2.csv").toFile();
        analysis.unavailable(
                source.getAbsolutePath(), target.getAbsolutePath()
        );
        try (BufferedReader bf = new BufferedReader(
                new FileReader(target))) {
            assertThat(bf.readLine())
                    .isEqualTo("10:57:01;11:02:02;");
        }
    }
}