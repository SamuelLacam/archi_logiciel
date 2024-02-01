package org.example.tp2.server;

import java.net.ServerSocket;
import java.io.IOException;
import java.net.Socket;

public class Serveur {
    
    private static ServerSocket ss;
    private static final int PORT = 432;
    
    public static void main(String [] args) {
        try {
            ServerSocket ss = new ServerSocket(PORT);
            System.out.println("Serveur lancé !");
            
            while (true) {
                Socket socket = ss.accept();
                System.out.println("client connecté");
                new ThreadsServeur(socket).start();
            }
        } catch (IOException e) {
                System.out.println(e.getMessage());
        }
        
    }
}