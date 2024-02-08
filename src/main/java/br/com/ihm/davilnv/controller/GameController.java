package br.com.ihm.davilnv.controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ihm.davilnv.bll.MuseumSystemBll;
import br.com.ihm.davilnv.dal.DatabaseConnection;
import br.com.ihm.davilnv.model.*;
import br.com.ihm.davilnv.utils.Config;
import br.com.ihm.davilnv.utils.ErrorHandler;
import br.com.ihm.davilnv.utils.MusicPlayer;
import br.com.ihm.davilnv.view.components.GameButton;
import br.com.ihm.davilnv.view.frames.MainFrame;
import br.com.ihm.davilnv.view.panels.*;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.table.TableModel;

@Getter
@Setter
public class GameController extends KeyAdapter implements ActionListener {
    private MainFrame mainFrame;
    private MapPanel mapPanel;
    private SuspectPanel suspectPanel;
    private Logic logic;
    private Player player;
    private MusicPlayer introGamePlayer;
    private static final GraphicsDevice DEVICE = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0]; // TODO : Mudar para monitor 0
    private static final int TARGET_FPS = 60;
    public static java.util.List<Rectangle> colisao;
    private boolean isRunning;

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
        player = new Player(8, 64, 64, 13, 21, 850, 1040, "/assets/images/sprite/sprite-detective_universal.png");

        DEVICE.setFullScreenWindow(mainFrame);

        for (GameButton button : mainFrame.buttons) {
            button.addActionListener(this);
        }

        mainFrame.addKeyListener(this);
        mainFrame.repaint();
        mainFrame.setFocusable(true);

    }

    private void iniciarJogo() {
        logic = new Logic();

        // Carrega a instancia da classe MapPanel e seta dados iniciais
        mapPanel = (MapPanel) mainFrame.getPanelByKey("map");
        mapPanel.createOffscreenImage();
        mapPanel.setLogic(logic);
        mapPanel.setPlayer(player);

        // Carrega as colisoes
        colisao = logic.getLayer("colision").buildColision();
        for (NPC npc : logic.getNpcs()) {
            colisao.add(npc.getPersonagemRectangle());
        }

        // Carrega o paneiol de suspeitos
        suspectPanel = (SuspectPanel) mainFrame.getPanelByKey("suspect");
        suspectPanel.setSuspectLabels(logic.getNpcs());

        // Chama  a inicialização do banco de dados
        DatabaseConnection.executeScript("/files/create_data_game.sql");

        // Inicia o game loop
        run();

    }

    public void montarMapa() {
        for (Layer layer : logic.getLayers()) {
            layer.buildMap();
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
                    mainFrame.getPanelByKey("newspaper").setVisible(true);
                    //mainFrame.getPanelByKey("map").setVisible(true);
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
        if (mapPanel != null)
            isRunning = !mapPanel.getDialogBox().isVisible();

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.out.println("Pause game");
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {

            if (player.getNearbyComputer(logic.getInfoPanelComputer())) {
                mapPanel.setVisible(false);
                suspectPanel.setVisible(true);
            }

            if (player.getNearbyComputer(logic.getComputer())) {

                // Pega a instancia do LoginPanel
                LoginPanel loginPanel = (LoginPanel) mainFrame.getPanelByKey("login");
                MuseumSystemPanel museumSystemPanel = (MuseumSystemPanel) mainFrame.getPanelByKey("museum-system");
                // Seta dados no MuseumSystemPanel
                museumSystemPanel.setTableList(MuseumSystemBll.getTableNames());

                // Esconde o MapPanel e mostra o LoginPanel
                mapPanel.setVisible(false);
                loginPanel.setVisible(true);

                // Adiciona ações aos botões
                loginPanel.getLoginButton().addActionListener(e1 -> {
                    String username = loginPanel.getUsernameField().getText().toLowerCase().trim();
                    String password = new String(loginPanel.getPasswordField().getPassword());
                    boolean logado = MuseumSystemBll.getLogin(username, password);
                    if (logado) {
                        if (loginPanel.getErrorLabel().isVisible())
                            loginPanel.getErrorLabel().setVisible(false);
                        // Realiza login, esconde o LoginPanel e mostra o MuseumSystemPanel
                        loginPanel.getUsernameField().setText(null);
                        loginPanel.getPasswordField().setText(null);
                        loginPanel.setVisible(false);
                        museumSystemPanel.setVisible(true);

                        // Adiciona ação ao botão de executar query e executa
                        museumSystemPanel.getExecuteButton().addActionListener(e2 -> {
                            String query = museumSystemPanel.getQueryField().getText();
                            TableModel tableModel;
                            try {
                                tableModel = MuseumSystemBll.executeQuery(query);
                                museumSystemPanel.setResultTable(tableModel);
                                List<String> correctAnswer = logic.checkAnswer(tableModel, mapPanel.getPhone().getCurrentQuestionIndex());
                                if (correctAnswer != null) {
                                    mapPanel.getPhone().getCurrentQuestion().setAnswered(true);
                                    mapPanel.getPhone().getCurrentQuestion().setAnswer(correctAnswer.toString());
                                }

                            } catch (SQLException ex) {
                                museumSystemPanel.setResultError(ex.getMessage());
                            }
                        });

                    } else {
                        loginPanel.getErrorLabel().setText("Usuário ou senha inválidos");
                        loginPanel.getErrorLabel().setVisible(true);
                        loginPanel.repaint();
                    }
                });

                loginPanel.getCloseButton().addActionListener(e1 -> {
                    loginPanel.setVisible(false);
                    mapPanel.setVisible(true);
                });

                museumSystemPanel.getCloseButton().addActionListener(e1 -> {
                    museumSystemPanel.setVisible(false);
                    mapPanel.setVisible(true);
                });

                suspectPanel.getCloseButton().addActionListener(e1 -> {
                    suspectPanel.setVisible(false);
                    mapPanel.setVisible(true);
                });

            }

            // Preenche o NPC que está próximo do personagem
            NPC nearbyNPC = player.getNearbyNPC(logic.getNpcs());
            if (nearbyNPC != null) {
                mapPanel.getDialogBox().setDialogues(nearbyNPC.getDialogues());
                mapPanel.getDialogBox().setScene(nearbyNPC.getScene());
                mapPanel.getDialogBox().setVisible(true);
            }

        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE && mainFrame.getCurrentPanel().getKey().equals("newspaper")) {
            NewspaperPanel newspaperPanel = (NewspaperPanel) mainFrame.getPanelByKey("newspaper");
            if (newspaperPanel.isFinished()) {
                // Inicia a tela de loading
                mainFrame.getPanelByKey("loading").setVisible(true);
                newspaperPanel.setVisible(false);

                // Cria um SwingWorker para executar o carregamento em segundo plano
                SwingWorker<Void, Void> worker = new SwingWorker<>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        newspaperPanel.getTimer().stop();
                        Thread.sleep(3000);  // Dorme por 3 segundos
                        return null;
                    }

                    @Override
                    protected void done() {
                        // Quando o carregamento estiver concluído, esconde a tela de loading e mostra o mapPanel
                        mainFrame.getPanelByKey("loading").setVisible(false);
                        mapPanel.setVisible(true);
                    }
                };

                // Inicia o SwingWorker
                worker.execute();
            } else {
                newspaperPanel.nextDialogue();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE && mapPanel != null && mapPanel.isVisible() && mapPanel.getDialogBox().isVisible() && player.getNearbyNPC() != null) {
            mapPanel.getDialogBox().nextDialogue();
            mapPanel.getDialogBox().nextScene();
        }

        if (e.getKeyCode() == KeyEvent.VK_I && mapPanel != null && mapPanel.isVisible()) {
            mapPanel.getPhone().setVisible(!mapPanel.getPhone().isVisible());
        }

        if (
                e.getKeyCode() == KeyEvent.VK_N
                        && mapPanel != null
                        && mapPanel.isVisible()
                        && mapPanel.getPhone().isVisible()
                        && mapPanel.getPhone().getCurrentQuestion().isAnswered()
        ) {
            mapPanel.getPhone().nextQuestion();
        }

        if (isRunning) {
            if (e.getKeyCode() == KeyEvent.VK_W) {
                player.setCima(true);
                player.setLastDirectionPressed(Player.Direction.UP);
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                player.setBaixo(true);
                player.setLastDirectionPressed(Player.Direction.DOWN);
            }
            if (e.getKeyCode() == KeyEvent.VK_A) {
                player.setEsquerda(true);
                player.setLastDirectionPressed(Player.Direction.LEFT);
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                player.setDireita(true);
                player.setLastDirectionPressed(Player.Direction.RIGHT);
            }

            player.movimento();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (isRunning) {
            if (e.getKeyCode() == KeyEvent.VK_W) player.setCima(false);
            if (e.getKeyCode() == KeyEvent.VK_S) player.setBaixo(false);
            if (e.getKeyCode() == KeyEvent.VK_A) player.setEsquerda(false);
            if (e.getKeyCode() == KeyEvent.VK_D) player.setDireita(false);

            if (!player.isCima() && !player.isBaixo() && !player.isEsquerda() && !player.isDireita()) {
                player.setLastDirectionPressed(null);
            }
        }
    }

}
