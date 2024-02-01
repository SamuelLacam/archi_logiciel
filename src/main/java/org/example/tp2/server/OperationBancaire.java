package org.example.tp2.server;

import org.example.tp2.donnees.User;
import org.example.tp2.messages.Erreur;
import org.example.tp2.messages.Retour;

public class OperationBancaire {

    private static User user = new User(800);

    static Retour fetchSolde() {
        Retour retour = new Retour(user.getSolde());
        return retour;
    }

    static Retour crediter(int montant) {
        user.setSolde(user.getSolde() + montant);
        Retour retour = new Retour(user.getSolde());
        return retour;
    }

    static Object debiter(int montant) {
        if (user.getSolde() >= montant) {
            user.setSolde(user.getSolde() - montant);
            Retour retour = new Retour(user.getSolde());
            return retour;
        } else {
            return new Erreur("solde insufisant");
        }
    }


}
