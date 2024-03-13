package logique;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Cette classe gère la lecture des mots à partir d'un fichier et fournit des méthodes pour sélectionner un mot aléatoire
 * ainsi que pour obtenir le mot et sa définition à partir d'un index donné.
 */
public class GestionMots {
    private static final String FICHIER_MOTS = "mots.txt"; // Chemin vers le fichier contenant les mots
    private ArrayList<String[]> mots; // Liste des mots avec leur définition

    /**
     * Constructeur de la classe GestionMots.
     *
     * @throws IOException Si une erreur d'entrée/sortie se produit lors de la lecture du fichier de mots.
     */
    public GestionMots() throws IOException {
        this.mots = lireMotsDepuisFichier();
    }

    /**
     * Lit les mots et leurs définitions à partir du fichier spécifié.
     *
     * @return Une liste contenant des tableaux de chaînes de caractères où chaque tableau représente un mot et sa définition.
     * @throws IOException Si une erreur d'entrée/sortie se produit lors de la lecture du fichier de mots.
     */
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

    /**
     * Sélectionne un mot aléatoire de la liste des mots.
     *
     * @return Un tableau de chaînes de caractères contenant le mot et sa définition.
     */
    public String[] tirerMotAleatoire() {
        Random random = new Random();
        int index = random.nextInt(mots.size());
        return mots.get(index);
    }

    /**
     * Récupère le mot à partir de l'index spécifié.
     *
     * @param index L'index du mot dans la liste.
     * @return Le mot correspondant à l'index spécifié, ou null si l'index est hors limites.
     */
    public String getMot(int index) {
        if (index >= 0 && index < mots.size()) {
            return mots.get(index)[0]; // Récupérer le mot à partir de l'index
        } else {
            return null; // Index hors limites
        }
    }

    /**
     * Récupère la définition du mot à partir de l'index spécifié.
     *
     * @param index L'index du mot dans la liste.
     * @return La définition du mot correspondant à l'index spécifié, ou null si l'index est hors limites.
     */
    public String getDefinition(int index) {
        if (index >= 0 && index < mots.size()) {
            return mots.get(index)[1]; // Récupérer la définition du mot à partir de l'index
        } else {
            return null; // Index hors limites
        }
    }
}
