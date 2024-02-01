/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.tp2.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
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
    
    private InterfaceModel model;
    
    @FXML
    private void connecter() {
        String ip = ipServeur.getText();
        int port = Integer.parseInt(this.port.getText());
        model = new InterfaceModel(ip, port);
        model.connexion();
    }
    
    @FXML
    private void afficherSolde() {
        Retour retour = model.afficherSolde();
        this.soldeActuelle.setText(String.valueOf(retour.getNouveauSolde()));
    }
    
    @FXML
    private void crediter() {
        int montant = Integer.parseInt((this.montant.getText()));
        Retour retour = model.crediter(montant);
        this.soldeActuelle.setText(String.valueOf(retour.getNouveauSolde()));
    }
    
    @FXML
    private void debiter() {
        int montant = Integer.parseInt((this.montant.getText()));

        Object object = model.debiter(montant);
        this.soldeActuelle.setText(String.valueOf(object));
        
    }
}
