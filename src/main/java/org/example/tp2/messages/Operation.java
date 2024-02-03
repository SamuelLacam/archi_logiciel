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
public class Operation implements Serializable {

    private static final String[] TYPE_OPERATIONS_1 = {"SOLDE", "FIN_DE_CONNEXION"};

    private static final String[] TYPE_OPERATIONS_2 = {"CREDITER", "DEBITER"};

    private String typeOperation;

    private int montant;

    public String getTypeOperation() {
        return typeOperation;
    }

    public int getMontant() {
        return montant;
    }

    public Operation(String typeOperation) {
        boolean resultat = false;
        for (String typeOp : TYPE_OPERATIONS_1) {
            if (typeOp.equals(typeOperation.toUpperCase())) {
                resultat = true;
            }
        }
        if (!resultat) {
            throw new IllegalArgumentException("Vous n'avez pas donné une opération valide");
        }

        this.typeOperation = typeOperation;
    }

    public Operation(String typeOperation, int montant) {
        if (montant <= 0) {
            throw  new IllegalArgumentException("montant donné invalide");
        }

        boolean resultat = false;
        for (String typeOp : TYPE_OPERATIONS_2) {
            if (typeOp.equals(typeOperation.toUpperCase())) {
                resultat = true;
            }
        }
        if (!resultat) {
            throw new IllegalArgumentException("Vous n'avez pas donné une opération valide");
        }

        this.typeOperation = typeOperation;
        this.montant = montant;
    }

}
