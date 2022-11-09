package ru.job4j.io;

import java.io.FileOutputStream;

public class MatrixResultFile {
    public static void main(String[] args) {
        int a = 9, b = 9;
        int[][] array = new int[9][9];
        try (FileOutputStream out = new FileOutputStream("MatrixResult.txt")) {
            for (int i = 0; i < a; i++) {
                for (int j = 0; j < b; j++) {
                    array[i][j] = (i + 1) * (j + 1);
                    out.write((array[i][j] + " ").getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}