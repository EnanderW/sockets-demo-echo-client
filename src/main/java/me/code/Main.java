package me.code;

public class Main {

    public static void main(String[] args) {
        new EchoClient("localhost", 5000).start();
    }
}
