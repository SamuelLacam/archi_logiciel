/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.tp2.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.example.tp2.messages.Erreur;
import org.example.tp2.messages.Retour;
import org.example.tp2.model.InterfaceModel;

import java.io.IOException;
import java.net.SocketException;

/**
 *
 * @author samuel.lacam
 */
public class InterfaceController {

    @FXML
    private TextField ipServeur;

    @FXML
    private TextField port;

    @FXML
    private TextField montant;

    @FXML
    private TextField soldeActuelle;

    private static InterfaceModel model = new InterfaceModel();

    @FXML
    private void connecter() {
        String ip = ipServeur.getText();
        try {
            int port = Integer.parseInt(this.port.getText());
            model.setIp(ip);
            model.setPort(port);
            model.connexion();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erreur, vous n'avez pas saisi un nombre entier pour le port");
            alert.show();
        }
    }

    @FXML
    private void afficherSolde() {
        try {
            Retour retour = model.afficherSolde();
            this.soldeActuelle.setText(String.valueOf(retour.getNouveauSolde()));
        } catch (IOException | ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erreur de communication avec le server");
            alert.show();
        }
    }

    @FXML
    private void crediter() {
        try {
            int montant = Integer.parseInt((this.montant.getText()));
            Retour retour = model.crediter(montant);
            this.soldeActuelle.setText(String.valueOf(retour.getNouveauSolde()));
        } catch (IOException | ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erreur de communication avec le server");
            alert.show();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erreur, vous n'avez pas saisi un nombre entier");
            alert.show();
        }
    }

    @FXML
    private void debiter() {
        try {
            int montant = Integer.parseInt((this.montant.getText()));
            Object recu = model.debiter(montant);
            if (recu instanceof Retour) {
                this.soldeActuelle.setText(String.valueOf(((Retour) recu).getNouveauSolde()));
            } else if (recu instanceof Erreur) {
                this.soldeActuelle.setText(((Erreur) recu).getMessageErreur());
            }
        } catch (IOException | ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erreur de communication avec le server");
            alert.show();
        }  catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erreur, vous n'avez pas saisi un nombre entier");
            alert.show();
        }
    }

    public static void deconnexion() {
        try {
            model.deconnexion();
        } catch (SocketException e) {
            // ne rien faire car fermuture de l'app
        }
    }
}
