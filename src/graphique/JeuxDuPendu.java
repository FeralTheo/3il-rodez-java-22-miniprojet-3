package graphique;

import logique.LogiquePendu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JeuxDuPendu extends JFrame implements KeyListener {
    private LogiquePendu LogiquePendu;
    private JLabel motLabel;
    private JLabel lettresSaisiesLabel;
    private JPanel penduPanel;
    private int etatPendu = 0;

    public JeuxDuPendu(String motSecret) {
        this.LogiquePendu = new LogiquePendu(motSecret);

        setTitle("Jeu du Pendu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout());

        motLabel = new JLabel(LogiquePendu.getMotAffiche(), SwingConstants.CENTER);
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

        lettresSaisiesLabel = new JLabel("Lettres saisies : ", SwingConstants.CENTER);
        add(lettresSaisiesLabel, BorderLayout.SOUTH);

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
        if (LogiquePendu.guessLetter(lettre)) {
            motLabel.setText(LogiquePendu.getMotAffiche());
            if (LogiquePendu.isGameWin()) {
                JOptionPane.showMessageDialog(this, "Félicitations ! Vous avez deviné le mot !");
                System.exit(0);
            }
        } else {
            String lettresSaisies = lettresSaisiesLabel.getText();
            lettresSaisies += lettre + " ";
            lettresSaisiesLabel.setText(lettresSaisies);

            if (etatPendu < 10) {
                etatPendu++;
                penduPanel.repaint();
            } else {
                JOptionPane.showMessageDialog(this, "Vous avez perdu la partie !");
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JeuxDuPendu penduGame = new JeuxDuPendu("Hello");
            penduGame.setVisible(true);
        });
    }
}
