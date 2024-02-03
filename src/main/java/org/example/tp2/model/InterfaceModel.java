/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.tp2.model;

import org.example.tp2.messages.Operation;
import org.example.tp2.messages.Retour;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

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

    public InterfaceModel() {

    }
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
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public Retour afficherSolde() throws IOException, ClassNotFoundException {
        if (!isSocketInitialize()) {
            throw new SocketException("Problème de connexion au serveur");
        }
        Operation operation = new Operation("SOLDE");
        out.writeObject(operation);
        return (Retour) in.readObject();
    }

    public Retour crediter(int montant) throws IOException, ClassNotFoundException {
        if (!isSocketInitialize()) {
            throw new SocketException("Problème de connexion au serveur");
        }
        Operation operation = new Operation("CREDITER", montant);
        out.writeObject(operation);
        return (Retour) in.readObject();
    }
    
    public Object debiter(int montant) throws IOException, ClassNotFoundException {
        if (!isSocketInitialize()) {
            throw new SocketException("Problème de connexion au serveur");
        }
        Operation operation = new Operation("DEBITER", montant);
        out.writeObject(operation);
        return in.readObject();
    }
    
    public void deconnexion() throws SocketException {
        if (!isSocketInitialize()) {
            throw new SocketException("Problème de connexion au serveur");
        }
        Operation operation = new Operation("FIN_DE_CONNEXION");
        try {
            out.writeObject(operation);
        } catch (IOException e) {
            // ne rien faire car on ferme l'app
        }
    }

    private boolean isSocketInitialize() {
        return socket != null && !socket.isClosed()
                && in != null && out != null;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
