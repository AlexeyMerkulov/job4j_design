package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        String startServerDown = null;
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            PrintWriter out = new PrintWriter(new BufferedOutputStream(
                    new FileOutputStream(target)));
            for (String s = in.readLine(); s != null; s = in.readLine()) {
                String[] array = s.split(" ");
                if ((array[0].equals("400") || array[0].equals("500")) && startServerDown == null) {
                    startServerDown = array[1];
                } else if ((array[0].equals("200") || array[0].equals("300")) && startServerDown != null) {
                    out.printf("%s%n", startServerDown + ";" + array[1] + ";");
                    startServerDown = null;
                }
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        /*
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
       */
        Analizy serverLog = new Analizy();
        serverLog.unavailable("serverlog.txt", "target.txt");
    }
}