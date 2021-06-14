package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String input = in.readLine();
                    if (input != null && !input.isEmpty()) {
                        System.out.println(input);
                        String answer = input.split("[= ]")[2];
                        switch (answer) {
                            case "Exit" -> server.close();
                            case "Hello" -> {
                                out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                                out.write("Hello".getBytes());
                            }
                            default -> {
                                out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                                out.write("What".getBytes());
                            }
                        }
                    } else {
                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        out.write("Enter not empty phrase".getBytes());
                    }
                }
            }
        }
    }
}