package logique;

public class LogiquePendu {
    private String motSecret;
    private StringBuilder motAffiche;

    public LogiquePendu(String motSecret) {
        this.motSecret = motSecret.toUpperCase();
        this.motAffiche = new StringBuilder();
        for (int i = 0; i < motSecret.length(); i++) {
            motAffiche.append("_ ");
        }
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
}
