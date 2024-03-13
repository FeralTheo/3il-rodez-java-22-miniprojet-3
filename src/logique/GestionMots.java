package logique;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GestionMots {
    private static final String FICHIER_MOTS = "mots.txt";
    private ArrayList<String[]> mots;

    public GestionMots() throws IOException {
        this.mots = lireMotsDepuisFichier();
    }

    private ArrayList<String[]> lireMotsDepuisFichier() throws IOException {
        ArrayList<String[]> mots = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FICHIER_MOTS))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] elements = ligne.split(" ", 2); // Séparer le mot de sa définition
                mots.add(elements); // Ajouter le tableau contenant le mot et sa définition à la liste
            }
        }
        return mots;
    }

    public String[] tirerMotAleatoire() {
        Random random = new Random();
        int index = random.nextInt(mots.size());
        return mots.get(index);
    }

    public String getMot(int index) {
        if (index >= 0 && index < mots.size()) {
            return mots.get(index)[0]; // Récupérer le mot à partir de l'index
        } else {
            return null; // Index hors limites
        }
    }

    public String getDefinition(int index) {
        if (index >= 0 && index < mots.size()) {
            return mots.get(index)[1]; // Récupérer la définition du mot à partir de l'index
        } else {
            return null; // Index hors limites
        }
    }
}
