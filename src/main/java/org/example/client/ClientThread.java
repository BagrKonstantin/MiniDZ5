package org.example.client;


import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientThread extends Thread {

    private final Scanner recieveMessageScanner;
    private final PrintWriter sendMessageWriter;
    private final String clientName;


    public ClientThread(String name, String host, int port) throws IOException {
        clientName = name;
        Socket clientSocket = new Socket(host, port);
        recieveMessageScanner = new Scanner(clientSocket.getInputStream());
        sendMessageWriter = new PrintWriter(clientSocket.getOutputStream());
    }


    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        try {
            while (true) {
                String message = in.nextLine();
                if (message.equals("F")) {
                    break;
                } else if (message.equals("X")) {
                    while (recieveMessageScanner.hasNextLine()) {
                        String inMes = recieveMessageScanner.nextLine();
                        System.out.println(inMes);
                    }
                } else {
                    sendMessageWriter.println(clientName + " : " + message);
                    sendMessageWriter.flush();
                }
            }
        } catch (Exception error) {
            System.out.println(error);
        }
    }


}

