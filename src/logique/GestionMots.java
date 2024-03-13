package logique;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GestionMots {
    private static final String FICHIER_MOTS = "mots.txt";

    public static ArrayList<String[]> lireMotsDepuisFichier() throws IOException {
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
}
