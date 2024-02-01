/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.tp2.server;

import org.example.tp2.messages.Operation;

import java.io.*;
import java.net.Socket;

/**
 *
 * @author samuel.lacam
 */
public class ThreadsServeur extends Thread {
    Socket socket;

    public ThreadsServeur(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run() {
        try {
            /*BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);*/

            /*String message = "";
            while (!message.equals("FIN")) {
                message = in.readLine();
                System.out.println("RECU " + message);
                out.println("RECU " + message);
            }*/

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            String demande = "";
            do {
                Object retour;
                System.out.println("eeeeeeee");
                Operation operation = (Operation) in.readObject();
                System.out.println(operation + " gg");
                demande = operation.getTypeOperation();
                System.out.println(demande + " jj");

                switch (demande) {
                    case "SOLDE":
                        retour = OperationBancaire.fetchSolde();
                        break;

                    case "CREDITER":
                        retour = OperationBancaire.crediter(operation.getMontant());
                        break;

                    case "DEBITER":
                        retour = OperationBancaire.debiter(operation.getMontant());
                        break;

                    //FIN_DE_CONNEXION
                    default:
                        retour = null;
                }
                out.writeObject(retour);
            } while (!demande.equals("FIN_DE_CONNEXION"));
            socket.close();

        } catch (IOException e) {
                System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());;
        }
    }
    
    
}
