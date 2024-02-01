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
public class Retour implements Serializable {

    private int nouveauSolde;

    public Retour(int nouveauSolde) {
        this.nouveauSolde = nouveauSolde;
    }

    public int getNouveauSolde() {
        return nouveauSolde;
    }
}
