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
        try {
            String ip = ipServeur.getText();
            int port = Integer.parseInt(this.port.getText());
            model.setIp(ip);
            model.setPort(port);
            model.connexion();
        } catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Veuillez donner un nombre entier pour le port");
            alert.show();
        } // TODO pourquoi pas mettre un time-out pour se connecter
    }
    
    @FXML
    private void afficherSolde() {
        Retour retour = model.afficherSolde();
        this.soldeActuelle.setText(String.valueOf(retour.getNouveauSolde()));
    }
    
    @FXML
    private void crediter() {
        try {
            int montant = Integer.parseInt((this.montant.getText()));
            Retour retour = model.crediter(montant);

            this.soldeActuelle.setText(String.valueOf(retour.getNouveauSolde()));
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Veuillez donner un nombre entier pour le montant");
            alert.show();
        }
    }
    
    @FXML
    private void debiter() {
        try {
            int montant = Integer.parseInt((this.montant.getText()));

            Object object = model.debiter(montant);
            if (object instanceof Retour) {
                String resultat = String.valueOf( ((Retour) object).getNouveauSolde() );
                this.soldeActuelle.setText(resultat);
            } else if (object instanceof Erreur) {
                String resultat = String.valueOf( ((Erreur) object).getMessageErreur());
                Alert alert = new Alert(Alert.AlertType.ERROR, resultat);
                alert.show();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Veuillez donner un nombre entier pour le montant");
            alert.show();
        }

    }

    public static void deconnexion() {
        model.deconnexion();
    }

}
