package br.com.ihm.davilnv.controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import br.com.ihm.davilnv.model.Camada;
import br.com.ihm.davilnv.model.Logica;
import br.com.ihm.davilnv.model.Personagem;
import br.com.ihm.davilnv.statics.MP3Player;
import br.com.ihm.davilnv.view.*;
import br.com.ihm.davilnv.view.components.GameButton;
import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;

public class GameController extends KeyAdapter implements ActionListener {
    MainFrame mainFrame;
    MapPanel mapPanel;
    Logica logica;
    Personagem personagem;
    MP3Player introGamePlayer;
    static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[1]; // TODO : Mudar para monitor 0
    public static java.util.List<Rectangle> colisao;
    boolean cima, baixo, direita, esquerda;
    int up, down, left, right;

    public GameController() {

        // Inicia a reprodução da música em uma thread separada
        Thread musicThread = new Thread(() -> {
            try {
                introGamePlayer = new MP3Player("/assets/audios/duck-dodgers-theme-song.mp3");
                introGamePlayer.playMp3InLoop();
            } catch (IOException | JavaLayerException e) {
                e.printStackTrace();
            }
        });
        musicThread.start();

        // Inicia a interface gráfica em outra thread
        SwingUtilities.invokeLater(() -> {
            try {
                createAndShowGUI();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Aguarde a conclusão da reprodução da música
        try {
            musicThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void createAndShowGUI() throws IOException {
        mainFrame = new MainFrame();

        personagem = new Personagem(8, 64, 64, 13, 21, 30, 500, "/assets/images/sprite/sprite-detective_universal.png");

        device.setFullScreenWindow(mainFrame);

        for (GameButton button : mainFrame.buttons) {
            button.addActionListener(this);
        }

        mainFrame.addKeyListener(this);

        mainFrame.setFocusable(true);

    }

    private void iniciarJogo() {
        logica = new Logica();
        mapPanel = (MapPanel) mainFrame.getPanelByKey("map");
        mapPanel.setLogica(logica);
        mapPanel.setPersonagem(personagem);
        colisao = logica.getCamada("colision").montarColisao();
        run();
    }

    public void montarMapa() {
		for (Camada camada : logica.getCamadas()) {
			camada.montarMapa();
		}
    }


    public void run() {
        Timer timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                montarMapa();
                mapPanel.repaint();
            }
        });

        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Game
        if (mainFrame.getButtonByKey("jogar") == e.getSource() && mainFrame.getCurrentPanel().getKey().equals("start")) {

            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    mainFrame.getPanelByKey("start").setVisible(false);
                    mainFrame.getPanelByKey("loading").setVisible(true);
                    mainFrame.getButtonByKey("jogar").setVisible(false);
                    mainFrame.getButtonByKey("seta-voltar").setVisible(false);
                    iniciarJogo();
                    Thread.sleep(8600);
                    return null;
                }
                @Override
                protected void done() {
                    mainFrame.getPanelByKey("loading").setVisible(false);
                    mainFrame.getPanelByKey("map").setVisible(true);
                }
            };

            worker.execute();
        }
		// Menu Buttons
        if (mainFrame.getButtonByKey("jogar") == e.getSource() && mainFrame.getCurrentPanel().getKey().equals("menu")) {
            mainFrame.disableMenuComponents("start");
            mainFrame.getButtonByKey("jogar").setVisible(true); // TODO: remove this line
        }
        if (mainFrame.getButtonByKey("config") == e.getSource()) {
            mainFrame.disableMenuComponents("config");
        }
        if (mainFrame.getButtonByKey("ranking") == e.getSource()) {
            mainFrame.disableMenuComponents("ranking");
        }
        if (mainFrame.getButtonByKey("tutorial") == e.getSource()) {
            mainFrame.disableMenuComponents("help");
        }
        if (mainFrame.getButtonByKey("creditos") == e.getSource()) {
            mainFrame.disableMenuComponents("credit");
        }
        if (mainFrame.getButtonByKey("sair") == e.getSource()) {
            System.exit(0);
        }
//		// Voltar
        if (mainFrame.getButtonByKey("seta-voltar") == e.getSource() && mainFrame.getCurrentPanel().getKey().equals("config")) {
            mainFrame.enableMenuComponents("config");
        }
        if (mainFrame.getButtonByKey("seta-voltar") == e.getSource() && mainFrame.getCurrentPanel().getKey().equals("start")) {
            mainFrame.enableMenuComponents("start");
        }
        if (mainFrame.getButtonByKey("seta-voltar") == e.getSource() && mainFrame.getCurrentPanel().getKey().equals("ranking")) {
            mainFrame.enableMenuComponents("ranking");
        }
        if (mainFrame.getButtonByKey("seta-voltar") == e.getSource() && mainFrame.getCurrentPanel().getKey().equals("help")) {
            mainFrame.enableMenuComponents("help");
        }
        if (mainFrame.getButtonByKey("seta-voltar") == e.getSource() && mainFrame.getCurrentPanel().getKey().equals("credit")) {
            mainFrame.enableMenuComponents("credit");
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.out.println("Pause game");
        }

        if (e.getKeyCode() == KeyEvent.VK_W) cima = true;
        if (e.getKeyCode() == KeyEvent.VK_S) baixo = true;
        if (e.getKeyCode() == KeyEvent.VK_A) esquerda = true;
        if (e.getKeyCode() == KeyEvent.VK_D) direita = true;

        movimento();

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) cima = false;
        if (e.getKeyCode() == KeyEvent.VK_S) baixo = false;
        if (e.getKeyCode() == KeyEvent.VK_A) esquerda = false;
        if (e.getKeyCode() == KeyEvent.VK_D) direita = false;
    }

    public void movimento() {
        if (esquerda) {
            int xL = personagem.getX();
            int yL = personagem.getY();
            if (xL > Personagem.DIFF_COLISAO) {
                personagem.setX(xL - Personagem.VELOCIDADE);
                personagem.setY(yL);
                switch (left) {
                    case 0:
                        personagem.setAparencia(9);
                        break;
                    case 1:
                        personagem.setAparencia(30);
                        break;
                    case 2:
                        personagem.setAparencia(51);
                        break;
                    case 3:
                        personagem.setAparencia(72);
                        break;
                    case 4:
                        personagem.setAparencia(93);
                        break;
                    case 5:
                        personagem.setAparencia(114);
                        break;
                    case 6:
                        personagem.setAparencia(135);
                        break;
                    case 7:
                        personagem.setAparencia(156);
                        break;
                    case 8:
                        personagem.setAparencia(177);
                        break;
                    case 9:
                        personagem.setAparencia(198);
                        break;
                    case 10:
                        personagem.setAparencia(219);
                        break;
                }
                if (left == 10) {
                    left = 0;
                } else {
                    left++;
                }

            }
        }
        if (cima) {
            int xL = personagem.getX();
            int yL = personagem.getY();
            if (yL > Personagem.DIFF_COLISAO) {
                personagem.setX(xL);
                personagem.setY(yL - Personagem.VELOCIDADE);
                switch (up) {
                    case 0:
                        personagem.setAparencia(8);
                        break;
                    case 1:
                        personagem.setAparencia(29);
                        break;
                    case 2:
                        personagem.setAparencia(50);
                        break;
                    case 3:
                        personagem.setAparencia(71);
                        break;
                    case 4:
                        personagem.setAparencia(92);
                        break;
                    case 5:
                        personagem.setAparencia(113);
                        break;
                    case 6:
                        personagem.setAparencia(134);
                        break;
                    case 7:
                        personagem.setAparencia(155);
                        break;
                    case 8:
                        personagem.setAparencia(176);
                        break;
                    case 9:
                        personagem.setAparencia(197);
                        break;
                    case 10:
                        personagem.setAparencia(218);
                        break;
                }
                if (up == 10) {
                    up = 0;
                } else {
                    up++;
                }
            }

        }
        if (direita) {
            int xL = personagem.getX();
            int yL = personagem.getY();
            if (xL < 1856-Personagem.DIFF_COLISAO) {
                personagem.setX(xL + Personagem.VELOCIDADE);
                personagem.setY(yL);
                switch (right) {
                    case 0:
                        personagem.setAparencia(11);
                        break;
                    case 1:
                        personagem.setAparencia(32);
                        break;
                    case 2:
                        personagem.setAparencia(53);
                        break;
                    case 3:
                        personagem.setAparencia(74);
                        break;
                    case 4:
                        personagem.setAparencia(95);
                        break;
                    case 5:
                        personagem.setAparencia(116);
                        break;
                    case 6:
                        personagem.setAparencia(137);
                        break;
                    case 7:
                        personagem.setAparencia(158);
                        break;
                    case 8:
                        personagem.setAparencia(179);
                        break;
                    case 9:
                        personagem.setAparencia(200);
                        break;
                    case 10:
                        personagem.setAparencia(221);
                        break;
                }
                if (right == 10) {
                    right = 0;
                } else {
                    right++;
                }
            }
        }
        if (baixo) {
            int xL = personagem.getX();
            int yL = personagem.getY();
            if (yL < 1016-Personagem.DIFF_COLISAO) {
                personagem.setX(xL);
                personagem.setY(yL + Personagem.VELOCIDADE);
                switch (down) {
                    case 0:
                        personagem.setAparencia(10);
                        break;
                    case 1:
                        personagem.setAparencia(31);
                        break;
                    case 2:
                        personagem.setAparencia(52);
                        break;
                    case 3:
                        personagem.setAparencia(73);
                        break;
                    case 4:
                        personagem.setAparencia(94);
                        break;
                    case 5:
                        personagem.setAparencia(115);
                        break;
                    case 6:
                        personagem.setAparencia(136);
                        break;
                    case 7:
                        personagem.setAparencia(157);
                        break;
                    case 8:
                        personagem.setAparencia(178);
                        break;
                    case 9:
                        personagem.setAparencia(199);
                        break;
                    case 10:
                        personagem.setAparencia(220);
                        break;
                }
                if (down == 10) {
                    down = 0;
                } else {
                    down++;
                }

            }
        }
    }

}
