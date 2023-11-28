package br.com.ihm.davilnv.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLoopExample {
    private static int x = 0;
    private static int y = 0;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Game Loop Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);

        frame.setVisible(true);

        // Inicia o game loop usando Timer
        Timer timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Atualiza a posição
                x += 5;
                y += 5;

                // Repinta o painel
                gamePanel.repaint();
            }
        });

        timer.start();
    }

    // JPanel personalizado para desenhar na tela
    static class GamePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Desenha um retângulo na posição atual (x, y)
            g.fillRect(x, y, 60, 60);
        }
    }
}
