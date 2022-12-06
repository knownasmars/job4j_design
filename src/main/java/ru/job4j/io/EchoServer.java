package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String line = in.readLine();
                    if (line.contains("/?msg=Exit")) {
                        out.write("Server is stopped!!!".getBytes());
                        server.close();
                    } else if (line.contains("/?msg=Hello")) {
                        out.write("Hello, dear friend!".getBytes());
                    } else {
                        out.write("What".getBytes());
                    }
                    out.flush();
                } catch (IOException ioe) {
                    LOG.error(
                            "Ошибка ВВОДА/ВЫВОДА данных", ioe
                    );
                }
            }
        } catch (Exception e) {
            LOG.error(
                    "Ошибка в основной части кода", e
            );
        }
    }
}