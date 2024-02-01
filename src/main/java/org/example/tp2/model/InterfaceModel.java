/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.tp2.model;

import org.example.tp2.messages.Operation;
import org.example.tp2.messages.Retour;

import java.io.*;
import java.net.Socket;

/**
 *
 * @author samuel.lacam
 */
public class InterfaceModel {
    
    private int port;
    
    private String ip;
    
    private ObjectInputStream in;
    
    private ObjectOutputStream out;
    
    private Socket socket;
    
    public InterfaceModel(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
    
    public void connexion() {
        System.out.println(ip + " " + port);
        try {
            socket = new Socket(ip, port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            afficherSolde();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public Retour afficherSolde() {
        if (this.isConnected()) {
            Operation operation = new Operation("SOLDE");
            envoieDonnee(operation);
            return (Retour) retourDonnee();
        }
        return null;
    }

    public Retour crediter(int montant) {
        if (this.isConnected()) {
            Operation operation = new Operation("CREDITER", montant);
            envoieDonnee(operation);
            return (Retour) retourDonnee();
        }
        return null;
    }
    
    public Object debiter(int montant) {
        if (this.isConnected()) {
            Operation operation = new Operation("DEBITER", montant);
            envoieDonnee(operation);
            return retourDonnee();
        }
        return null;
    }

    private void envoieDonnee(Operation operation) {
        try {
            out.writeObject(operation);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private Object retourDonnee() {
        try {
            return (Retour) in.readObject();
        } catch (ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public void deconnexion()  {
        if (this.isConnected()) {
            Operation operation = new Operation("FIN_DE_CONNEXTION");
        }
    }

    private boolean isConnected() {
        return !socket.isClosed();
    }
}
