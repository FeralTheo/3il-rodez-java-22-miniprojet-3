package test;

import logique.GestionMots;
import logique.LogiquePendu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

public class PenduTest {

    @Test
    public void testGuessLetter() {
        LogiquePendu jeu = new LogiquePendu("TEST");
        assertTrue(jeu.guessLetter('T'));
        assertFalse(jeu.guessLetter('A'));
        assertTrue(jeu.guessLetter('S'));
        assertEquals("T _ S T ", jeu.getMotAffiche());
    }

    @Test
    public void testIsGameWin() {
        LogiquePendu jeu = new LogiquePendu("TEST");
        assertFalse(jeu.isGameWin());
        jeu.guessLetter('T');
        jeu.guessLetter('E');
        jeu.guessLetter('S');
        assertTrue(jeu.isGameWin());
    }

    @Test
    public void testReset() {
        LogiquePendu jeu = new LogiquePendu("TEST");
        jeu.reset();
        assertEquals("_ _ _ _ ", jeu.getMotAffiche());
    }

    @Test
    public void testTirerMotAleatoire() {
        try {
            GestionMots gestionMots = new GestionMots();
            String[] motAleatoire = gestionMots.tirerMotAleatoire();
            assertNotNull(motAleatoire);
            assertNotNull(motAleatoire[0]);
            assertNotNull(motAleatoire[1]);
        } catch (IOException e) {
            fail("Erreur lors de la récupération des mots aléatoires : " + e.getMessage());
        }
    }
}
