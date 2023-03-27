package org.example.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private ArrayList<ClientConnector> clients;

    public Server(int port) throws IOException {
        clients = new ArrayList<>();
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server online");
        while (true) {
            Socket clientSocket = serverSocket.accept();
            ClientConnector client = new ClientConnector(clientSocket, this);
            clients.add(client);
            client.start();
        }
    }

    public void sendMessageToEveryOne(String msg) {
        for (var client : clients) {
            client.sendMsg(msg);
        }
    }
}
