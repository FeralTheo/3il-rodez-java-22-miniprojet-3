package tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import logique.GestionMots;

public class GestionMotsTest {

    public static void main(String[] args) {
        try {
            ArrayList<String[]> mots = GestionMots.lireMotsDepuisFichier();
            if (!mots.isEmpty()) {
                Random random = new Random();
                int indexMotAleatoire = random.nextInt(mots.size());
                String[] motAleatoire = mots.get(indexMotAleatoire);
                String mot = motAleatoire[0];
                String definition = motAleatoire[1];
                System.out.println("Le mot choisi est : " + mot);
                System.out.println("La définition du mot est : " + definition);
            } else {
                System.out.println("Aucun mot trouvé dans le fichier.");
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier de mots : " + e.getMessage());
        }
    }
}
