package graphique;

import logique.GestionMots;
import logique.LogiquePendu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class JeuxDuPendu extends JFrame implements KeyListener {
    private LogiquePendu logiquePendu;
    private JLabel motLabel;
    private JLabel lettresSaisiesLabel;
    private JLabel definitionLabel;
    private JPanel penduPanel;
    private int etatPendu = 0;

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

        // Créer un conteneur pour les labels lettresSaisiesLabel et definitionLabel
        JPanel labelsPanel = new JPanel(new GridLayout(2, 1));
        // Ajouter les labels au conteneur
        labelsPanel.add(lettresSaisiesLabel = new JLabel("Lettres saisies : ", SwingConstants.CENTER));
        labelsPanel.add(definitionLabel); // Ajouter le label de la définition au conteneur

        // Ajouter le conteneur à la partie sud de la fenêtre
        add(labelsPanel, BorderLayout.SOUTH);

        addKeyListener(this);
        setFocusable(true);
    }


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
        // Dessiner le pendu en fonction du nombre d'étapes
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

    @Override
    public void keyTyped(KeyEvent e) {
        char lettre = e.getKeyChar();
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


    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JeuxDuPendu penduGame = new JeuxDuPendu();
            penduGame.setVisible(true);
        });
    }
}
