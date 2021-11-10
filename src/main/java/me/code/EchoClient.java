package me.code;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {

    private final String host;
    private final int port;
    private Socket socket;
    private Scanner scanner;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        try {
            this.socket = new Socket(this.host, this.port);

            OutputStream outputStream = this.socket.getOutputStream();
            InputStream inputStream = this.socket.getInputStream();

            String line = scanner.nextLine();
            while (!line.equalsIgnoreCase("exit")) {

                outputStream.write(line.getBytes());
                outputStream.flush();

                byte[] buffer = new byte[256];
                int count = inputStream.read(buffer);

                System.out.println("From server: " + new String(buffer, 0, count));

                line = scanner.nextLine();
            }

            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
