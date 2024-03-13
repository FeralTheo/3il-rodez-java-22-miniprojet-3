package logique;

import java.io.IOException;

/**
 * Cette classe représente la logique métier du jeu du pendu.
 */
public class LogiquePendu {
    private String motSecret; // Le mot secret à deviner
    private StringBuilder motAffiche; // La représentation du mot à deviner affichée à l'utilisateur
    private String definition; // La définition associée au mot secret

    /**
     * Constructeur de la classe LogiquePendu.
     *
     * @param motSecret Le mot secret à deviner
     */
    public LogiquePendu(String motSecret) {
        this.motSecret = motSecret.toUpperCase(); // Convertir le mot secret en majuscules
        this.motAffiche = new StringBuilder();
        // Initialiser la représentation du mot à deviner avec des tirets bas pour chaque lettre du mot secret
        for (int i = 0; i < motSecret.length(); i++) {
            motAffiche.append("_ ");
        }
        this.definition = ""; // Initialiser la définition à une chaîne vide
    }

    /**
     * Récupère la représentation du mot à deviner.
     *
     * @return La représentation du mot à deviner
     */
    public String getMotAffiche() {
        return motAffiche.toString();
    }

    /**
     * Vérifie si la lettre proposée par l'utilisateur est présente dans le mot secret.
     *
     * @param lettre La lettre proposée par l'utilisateur
     * @return true si la lettre est présente dans le mot secret, sinon false
     */
    public boolean guessLetter(char lettre) {
        lettre = Character.toUpperCase(lettre); // Convertir la lettre en majuscule
        if (Character.isLetter(lettre)) {
            if (motSecret.indexOf(lettre) != -1) {
                for (int i = 0; i < motSecret.length(); i++) {
                    if (motSecret.charAt(i) == lettre) {
                        // Remplacer les tirets bas par la lettre correcte aux positions correspondantes
                        motAffiche.setCharAt(i * 2, lettre);
                    }
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Vérifie si le jeu est gagné (c'est-à-dire si toutes les lettres ont été devinées).
     *
     * @return true si le jeu est gagné, sinon false
     */
    public boolean isGameWin() {
        return motAffiche.indexOf("_") == -1;
    }

    /**
     * Réinitialise le jeu en choisissant un nouveau mot secret aléatoire.
     * Réinitialise également la représentation du mot à deviner.
     */
    public void reset() {
        try {
            GestionMots gestionMots = new GestionMots();
            String[] motAleatoire = gestionMots.tirerMotAleatoire();
            this.motSecret = motAleatoire[0]; // Choisir un nouveau mot secret
            this.definition = motAleatoire[1]; // Mettre à jour la définition
            // Réinitialiser la représentation du mot à deviner avec des tirets bas pour chaque lettre du nouveau mot secret
            this.motAffiche = new StringBuilder();
            for (int i = 0; i < motSecret.length(); i++) {
                motAffiche.append("_ ");
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la récupération des mots aléatoires : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
