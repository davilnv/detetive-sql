package br.com.ihm.davilnv.controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import br.com.ihm.davilnv.bll.MuseumSystemBll;
import br.com.ihm.davilnv.dal.DatabaseConnection;
import br.com.ihm.davilnv.model.*;
import br.com.ihm.davilnv.utils.Config;
import br.com.ihm.davilnv.utils.ErrorHandler;
import br.com.ihm.davilnv.utils.MusicPlayer;
import br.com.ihm.davilnv.view.*;
import br.com.ihm.davilnv.view.components.GameButton;
import lombok.Getter;

import javax.swing.*;

public class GameController extends KeyAdapter implements ActionListener {
    private MainFrame mainFrame;
    @Getter
    private MapPanel mapPanel;
    private Logica logica;
    private Personagem personagem;
    private MusicPlayer introGamePlayer;
    private static final GraphicsDevice DEVICE = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0]; // TODO : Mudar para monitor 0
    private static final int TARGET_FPS = 60;
    public static java.util.List<Rectangle> colisao;



    public GameController() {

        // Inicia a reprodução da música em uma thread separada
        Thread musicThread = new Thread(() -> {
            // Carrega as configurações do jogo (volume, idioma, etc)
            try {
                Config.load();
            } catch (IOException e) {
                ErrorHandler.logAndExit(e);
            }

            introGamePlayer = new MusicPlayer("/audios/duck-dodgers-theme-song.mp3");
            introGamePlayer.playInLoop();
        });
        musicThread.start();

        // Inicia a interface gráfica em outra thread
        SwingUtilities.invokeLater(this::createAndShowGUI);

        // Aguarde a conclusão da reprodução da música
        try {
            musicThread.join();
        } catch (InterruptedException e) {
            ErrorHandler.logAndExit(e);
        }

    }

    private void createAndShowGUI() {

        mainFrame = new MainFrame();
        // TODO: Mudar posição do personagem para iniciar na porta do museu
        personagem = new Personagem(8, 64, 64, 13, 21, 136, 212, "/assets/images/sprite/sprite-detective_universal.png");

        DEVICE.setFullScreenWindow(mainFrame);

        for (GameButton button : mainFrame.buttons) {
            button.addActionListener(this);
        }

        mainFrame.addKeyListener(this);

        mainFrame.setFocusable(true);

    }

    private void iniciarJogo() {
        logica = new Logica();

        // Carrega a instancia da classe MapPanel e seta dados iniciais
        mapPanel = (MapPanel) mainFrame.getPanelByKey("map");
        mapPanel.createOffscreenImage();
        mapPanel.setLogica(logica);
        mapPanel.setPersonagem(personagem);

        // Carrega as colisoes
        colisao = logica.getCamada("colision").montarColisao();
        for (NPC npc : logica.getNpcs()) {
            colisao.add(npc.getPersonagemRectangle());
        }
//        colisao.add(logica.getComputador().getRectangle());

        // Chama  a inicialização do banco de dados
        DatabaseConnection.executeScript("/files/create_data_game.sql");

        // Inicia o game loop
        run();

    }

    public void montarMapa() {
        for (Camada camada : logica.getCamadas()) {
            camada.montarMapa();
        }
    }

    public void run() {
        GameLoop gameLoop = new GameLoop(TARGET_FPS, this);
        gameLoop.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Game
        if (mainFrame.getButtonByKey("jogar") == e.getSource() && mainFrame.getCurrentPanel().getKey().equals("start")) {

            SwingWorker<Void, Void> worker = new SwingWorker<>() {
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
            ConfigPanel configPanel = (ConfigPanel) mainFrame.getCurrentPanel();
            configPanel.setMusicPlayer(introGamePlayer);
            configPanel.initVolumeSlider();
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

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {

            if (personagem.getNearbyComputer(logica.getComputador())) {
                System.out.println("Interagindo com o computador");

                // Pega a instancia do LoginPanel
                LoginPanel loginPanel = (LoginPanel) mainFrame.getPanelByKey("login");
                mapPanel.setVisible(false);
                loginPanel.setVisible(true);

                // Adiciona ações aos botões
                loginPanel.getLoginButton().addActionListener(e1 -> {
                    String username = loginPanel.getUsernameField().getText();
                    String password = new String(loginPanel.getPasswordField().getPassword());
                    boolean logado = MuseumSystemBll.getLogin(username, password);
                    if (logado) {
                        System.out.println("Login realizado com sucesso");
//                        loginPanel.setVisible(false);
//                        mapPanel.setVisible(true);
                    } else {
                        System.out.println("Usuário ou senha inválidos");
                    }
                    // Implemente a lógica de login aqui
                });
                loginPanel.getCloseButton().addActionListener(e1 -> {
                    loginPanel.setVisible(false);
                    mapPanel.setVisible(true);
                });

            }

            NPC nearbyNPC = personagem.getNearbyNPC(logica.getNpcs());
            if (nearbyNPC != null) {
                System.out.println("Interagindo com o NPC " + nearbyNPC.getNome());
                // Execute a ação desejada com o NPC
                // Por exemplo, você pode chamar um método do NPC:
                //nearbyNPC.interact();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_W) {
            personagem.setCima(true);
            personagem.setLastDirectionPressed(Personagem.Direction.UP);
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            personagem.setBaixo(true);
            personagem.setLastDirectionPressed(Personagem.Direction.DOWN);
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            personagem.setEsquerda(true);
            personagem.setLastDirectionPressed(Personagem.Direction.LEFT);
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            personagem.setDireita(true);
            personagem.setLastDirectionPressed(Personagem.Direction.RIGHT);
        }

        personagem.movimento();

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) personagem.setCima(false);
        if (e.getKeyCode() == KeyEvent.VK_S) personagem.setBaixo(false);
        if (e.getKeyCode() == KeyEvent.VK_A) personagem.setEsquerda(false);
        if (e.getKeyCode() == KeyEvent.VK_D) personagem.setDireita(false);

        if (!personagem.isCima() && !personagem.isBaixo() && !personagem.isEsquerda() && !personagem.isDireita()) {
            personagem.setLastDirectionPressed(null);
        }
    }

}
