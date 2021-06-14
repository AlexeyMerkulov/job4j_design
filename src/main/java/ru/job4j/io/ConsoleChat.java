package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    private List<String> listOfAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    private void getListOfAnswers() {
        listOfAnswers = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers))) {
            in.lines().forEach(listOfAnswers::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getAnswer() {
        return listOfAnswers.get(new Random().nextInt(listOfAnswers.size()));
    }

    public void run() {
        getListOfAnswers();
        List<String> rsl = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите фразу");
        String input = scanner.nextLine();
        String flag = CONTINUE;
        while (!input.equals(OUT)) {
            rsl.add(input);
            if (!flag.equals(STOP)) {
                switch (input) {
                    case STOP:
                        flag = STOP;
                        break;
                    case CONTINUE:
                        flag = CONTINUE;
                    default:
                        String answer = getAnswer();
                        System.out.println(answer);
                        rsl.add(answer);
                }
            } else if (input.equals(CONTINUE)) {
                flag = CONTINUE;
            }
            System.out.println("Введите фразу");
            input = scanner.nextLine();
        }
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
            rsl.forEach(s -> pw.println(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("C:\\Users\\Alexey\\Desktop\\IO\\log.txt",
                "C:\\Users\\Alexey\\Desktop\\IO\\answers.txt");
        cc.run();
    }
}