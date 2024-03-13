package logique;

import java.io.IOException;

public class LogiquePendu {
    private String motSecret;
    private StringBuilder motAffiche;
	private String definition;

	public LogiquePendu(String motSecret) {
        this.motSecret = motSecret.toUpperCase();
        this.motAffiche = new StringBuilder();
        for (int i = 0; i < motSecret.length(); i++) {
            motAffiche.append("_ ");
        }
        this.definition = ""; // Initialiser la définition à une chaîne vide
    }

    public String getMotAffiche() {
        return motAffiche.toString();
    }

    public boolean guessLetter(char lettre) {
        lettre = Character.toUpperCase(lettre);
        if (Character.isLetter(lettre)) {
            if (motSecret.indexOf(lettre) != -1) {
                for (int i = 0; i < motSecret.length(); i++) {
                    if (motSecret.charAt(i) == lettre) {
                        motAffiche.setCharAt(i * 2, lettre);
                    }
                }
                return true;
            }
        }
        return false;
    }

    public boolean isGameWin() {
        return motAffiche.indexOf("_") == -1;
    }
    
    public void reset() {
        try {
            GestionMots gestionMots = new GestionMots();
            String[] motAleatoire = gestionMots.tirerMotAleatoire();
            this.motSecret = motAleatoire[0];
            this.definition = motAleatoire[1];
            // Réinitialiser motAffiche si nécessaire
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
