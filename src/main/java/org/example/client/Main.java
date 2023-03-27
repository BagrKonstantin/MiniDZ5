package org.example.client;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Enter your name");
        Scanner in = new Scanner(System.in);
        new ClientThread(in.nextLine(), "localhost", 3737).start();
    }
}
