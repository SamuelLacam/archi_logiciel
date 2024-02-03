/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.tp2.messages;

import java.io.Serializable;

/**
 *
 * @author samuel.lacam
 */
public class Erreur implements Serializable {

    private String messageErreur;

    public Erreur(String messageErreur) {
        this.messageErreur = messageErreur;
    }

    public String getMessageErreur() {
        return messageErreur;
    }
}
