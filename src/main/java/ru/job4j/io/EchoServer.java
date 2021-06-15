package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String input = in.readLine();
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    if (input != null && !input.isEmpty()) {
                        System.out.println(input);
                        String answer = input.split("[= ]")[2];
                        switch (answer) {
                            case "Exit" -> server.close();
                            case "Hello" -> out.write("Hello".getBytes());
                            default -> out.write("What".getBytes());
                        }
                    } else {
                        out.write("Enter not empty phrase".getBytes());
                    }
                }
            }
        } catch (IOException e) {
            LOG.error("Exception in log", e);
        }
    }
}