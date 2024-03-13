package graphique;

import logique.GestionMots;
import logique.LogiquePendu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

/**
 * Cette classe représente l'interface graphique du jeu du Pendu.
 */
public class JeuxDuPendu extends JFrame implements KeyListener {
    private LogiquePendu logiquePendu; // Objet de la logique métier du jeu du Pendu
    private JLabel motLabel; // Label pour afficher le mot à deviner
    private JLabel lettresSaisiesLabel; // Label pour afficher les lettres déjà saisies par l'utilisateur
    private JLabel definitionLabel; // Label pour afficher la définition du mot à deviner
    private JLabel tentativesRestantesLabel; // Label pour afficher le nombre de tentatives restantes
    private JPanel penduPanel; // Panel pour dessiner le pendu
    private int etatPendu = 0; // État du pendu

    /**
     * Constructeur de la classe JeuxDuPendu.
     */
    public JeuxDuPendu() {
        super("Jeu du Pendu");
        try {
            GestionMots gestionMots = new GestionMots();
            String[] motAleatoire = gestionMots.tirerMotAleatoire();
            logiquePendu = new LogiquePendu(motAleatoire[0]);
            // Assigner la définition au label
            definitionLabel = new JLabel("Définition : " + motAleatoire[1], SwingConstants.CENTER);
        } catch (IOException e) {
            System.err.println("Erreur lors de la récupération des mots aléatoires : " + e.getMessage());
            e.printStackTrace();
        }

        setTitle("Jeu du Pendu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout());

        motLabel = new JLabel(logiquePendu.getMotAffiche(), SwingConstants.CENTER);
        motLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(motLabel, BorderLayout.NORTH);

        penduPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dessinerPendu(g);
            }
        };
        add(penduPanel, BorderLayout.CENTER);

        // Créer un conteneur pour les labels lettresSaisiesLabel, definitionLabel et tentativesRestantesLabel
        JPanel labelsPanel = new JPanel(new GridLayout(3, 1));
        // Ajouter les labels au conteneur
        labelsPanel.add(lettresSaisiesLabel = new JLabel("Lettres saisies : ", SwingConstants.CENTER));
        labelsPanel.add(definitionLabel); // Ajouter le label de la définition au conteneur
        labelsPanel.add(tentativesRestantesLabel = new JLabel("Tentatives restantes : " + (11 - etatPendu), SwingConstants.CENTER)); // Ajouter le label pour afficher les tentatives restantes
        // Ajouter le conteneur à la partie sud de la fenêtre
        add(labelsPanel, BorderLayout.SOUTH);

        addKeyListener(this);
        setFocusable(true);
    }

    /**
     * Dessine le pendu en fonction de l'état actuel.
     *
     * @param g L'objet Graphics utilisé pour dessiner.
     */
    private void dessinerPendu(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int width = penduPanel.getWidth();
        int height = penduPanel.getHeight();

        // Dessiner la potence
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(5));
        if (etatPendu >= 1) {
            g2d.drawLine(width / 4 - 30, height - 50, width / 2 + 30, height - 50);
        }
        if (etatPendu >= 2) {
            g2d.drawLine(width / 4, height - 50, width / 4, height / 4);
        }
        if (etatPendu >= 3) {
            g2d.drawLine(width / 4, height / 4, width / 2, height / 4);
        }
        if (etatPendu >= 4) {
            g2d.drawLine(width / 4, height / 4 + 50, width / 3, height / 4);
        }
        if (etatPendu >= 5) {
            g2d.drawLine(width / 2, height / 4, width / 2, height / 4 + 10);
        }
        // Dessiner le pendu
        if (etatPendu >= 6) {
            g2d.drawOval(width / 2 - 20, height / 4 + 10, 40, 40); // Tête
        }
        if (etatPendu >= 7) {
            g2d.drawLine(width / 2, height / 4 + 50, width / 2, height / 4 + 150); // Corps
        }
        if (etatPendu >= 8) {
            g2d.drawLine(width / 2, height / 4 + 70, width / 2 - 50, height / 4 + 100); // Bras gauche
        }
        if (etatPendu >= 9) {
            g2d.drawLine(width / 2, height / 4 + 70, width / 2 + 50, height / 4 + 100); // Bras droit
        }
        if (etatPendu >= 10) {
            g2d.drawLine(width / 2, height / 4 + 150, width / 2 - 40, height / 4 + 200); // Jambe gauche
        }
        if (etatPendu >= 11) {
            g2d.drawLine(width / 2, height / 4 + 150, width / 2 + 40, height / 4 + 200); // Jambe droite
        }
    }

    /**
     * Gère l'événement de saisie de touche.
     *
     * @param e L'événement de saisie de touche.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        char lettre = e.getKeyChar();
        
        // Vérifier si la touche pressée est une lettre, un espace ou un tiret
        if (!Character.isLetter(lettre) && lettre != ' ' && lettre != '-') {
            e.consume(); // Ignorer la touche pressée si elle n'est pas une lettre, un espace ou un tiret
            return;
        }
        
        if (logiquePendu.guessLetter(lettre)) {
            motLabel.setText(logiquePendu.getMotAffiche());
            if (logiquePendu.isGameWin()) {
                JOptionPane.showMessageDialog(this, "Félicitations ! Vous avez deviné le mot !");
                restartGame();
            }
        } else {
            String lettresSaisies = lettresSaisiesLabel.getText();
            lettresSaisies += lettre + " ";
            lettresSaisiesLabel.setText(lettresSaisies);

            etatPendu++;
            penduPanel.repaint();

            if (etatPendu >= 11) {
                JOptionPane.showMessageDialog(this, "Vous avez perdu la partie !");
                restartGame();
            }
        }
    }

    /**
     * Réinitialise le jeu après la fin de la partie.
     */
    private void restartGame() {
        // Effacer les informations précédentes
        etatPendu = 0;
        lettresSaisiesLabel.setText("Lettres saisies : ");
        logiquePendu.reset(); // Réinitialiser le jeu

        // Mettre à jour l'affichage du mot
        motLabel.setText(logiquePendu.getMotAffiche()); // Mettre à jour le texte du label avec le nouveau mot

        // Redessiner le pendu
        penduPanel.repaint();
    }

    /**
     * Gère l'événement de pression d'une touche.
     *
     * @param e L'événement de pression d'une touche.
     */
    @Override
    public void keyPressed(KeyEvent e) {}

    /**
     * Gère l'événement de relâchement d'une touche.
     *
     * @param e L'événement de relâchement d'une touche.
     */
    @Override
    public void keyReleased(KeyEvent e) {}

    /**
     * Point d'entrée du programme.
     *
     * @param args Les arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JeuxDuPendu penduGame = new JeuxDuPendu();
            penduGame.setVisible(true);
        });
    }
}
