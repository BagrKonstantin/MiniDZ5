package org.example.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientConnector extends Thread {
		
    private Server server;
    private PrintWriter sendMessageWriter;
    private Scanner recieveMessageScanner;


    public ClientConnector(Socket socket, Server server) {
        try {
            this.server = server;
            this.sendMessageWriter = new PrintWriter(socket.getOutputStream());
            this.recieveMessageScanner = new Scanner(socket.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (recieveMessageScanner.hasNext()) {
                    String clientMessage = recieveMessageScanner.nextLine();
                    System.out.println(clientMessage);
                    server.sendMessageToEveryOne(clientMessage);
                }
                Thread.sleep(100);
            }
        }
        catch (InterruptedException error) {
            System.out.println(error);
        }
    }
    public void sendMsg(String msg) {
        try {
            sendMessageWriter.println(msg);
            sendMessageWriter.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
