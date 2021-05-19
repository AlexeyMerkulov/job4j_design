package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder sb = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                sb.append((char) read);
            }
            String[] strings = sb.toString().split(System.lineSeparator());
            for (String s : strings) {
                int i = Integer.parseInt(s);
                boolean isEven = i % 2 == 0;
                System.out.println(i + " является четным " + isEven);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
