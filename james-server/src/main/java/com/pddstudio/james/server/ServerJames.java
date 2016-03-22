package com.pddstudio.james.server;

import com.pddstudio.james.core.utils.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerJames {

    private static final int SERVER_PORT = 3012;

    private ServerSocket serverSocket;

    public static void main(String[] args) {
        ServerJames serverJames = new ServerJames();
        serverJames.startServer();
    }

    private void startServer() {
        Logger.log(this, "Starting Server on port " + SERVER_PORT);
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            while(true) {
                Socket clientSocket = serverSocket.accept();
                Logger.log(this, "Client connected: " + clientSocket.getInetAddress().getHostAddress());
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

}
