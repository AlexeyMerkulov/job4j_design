package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.StringJoiner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalizyTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenOneResultLine() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(
                new FileOutputStream(source)))) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("200 10:59:01");
        }
        Analizy test = new Analizy();
        test.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringJoiner rsl = new StringJoiner(System.lineSeparator());
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::add);
        }
        assertThat(rsl.toString(), is("10:57:01;10:59:01;"));
    }

    @Test
    public void whenTwoResultLines() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(
                new FileOutputStream(source)))) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("200 10:59:01");
            out.println("400 11:10:01");
            out.println("300 11:12:21");
        }
        Analizy test = new Analizy();
        test.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringJoiner rsl = new StringJoiner(System.lineSeparator());
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::add);
        }
        assertThat(rsl.toString(), is("10:57:01;10:59:01;"
                + System.lineSeparator() + "11:10:01;11:12:21;"));
    }
}