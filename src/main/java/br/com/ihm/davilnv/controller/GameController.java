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
import br.com.ihm.davilnv.view.*;
import br.com.ihm.davilnv.view.components.GameButton;

public class GameController extends KeyAdapter implements ActionListener{
	MainFrame mainFrame;
	MapPanel mapPanel;
	Logica logica;

	Personagem personagem;
	static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];

	boolean cima, baixo, direita, esquerda;
	int up, down, left, right;

	public GameController() throws IOException {
		mainFrame = new MainFrame();
		logica = new Logica();
		personagem = new Personagem(0, 32, 32, 10, 10, 500, 500, "/assets/images/sprite/sprite-detective_universal.png");

		mapPanel = (MapPanel) mainFrame.getPanelByKey("map");
		mapPanel.setLogica(logica);
		mapPanel.setPersonagem(personagem);

//		logica.getCamada("colision").montarColisao();

		device.setFullScreenWindow(mainFrame);

		for (GameButton button : mainFrame.buttons) {
			button.addActionListener(this);
		}

		mainFrame.addKeyListener(this);

		run();
	}
	public void montarMapa() {
		logica.getCamada("floor").montarMapa();
		logica.getCamada("second-floor").montarMapa();
		logica.getCamada("colision").montarMapa();
		logica.getCamada("top").montarMapa();
		logica.getCamada("front-top").montarMapa();
//		for (Camada camada : logica.getCamadas()) {
//			camada.montarMapa();
//		}
	}

	public void run() {
		while (true) {
			montarMapa();
			mapPanel.repaint();

			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

//		// Game
		if (mainFrame.getButtonByKey("jogar") == e.getSource() && mainFrame.getCurrentPanel().getKey().equals("start")) {
//			tela.getInventario().setMultplayer(multplayer);
//			menu.setVisible(false);
			mainFrame.getPanelByKey("start").setVisible(false);
			mainFrame.getPanelByKey("map").setVisible(true);
			mainFrame.getButtonByKey("jogar").setVisible(false);
			mainFrame.getButtonByKey("seta-voltar").setVisible(false);

		}
//		// Menu Buttons
		if (mainFrame.getButtonByKey("jogar") == e.getSource() && mainFrame.getCurrentPanel().getKey().equals("menu")) {
			mainFrame.disableMenuComponents("start");
			mainFrame.getButtonByKey("jogar").setVisible(true); // TODO: remove this line
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
//
//		if (tela.getInventario().getSairButton() == e.getSource()) {
//			tela.setVisible(false);
//			menu.setVisible(true);
//			personagens.get(0).reset(236, 236);
//			personagens.get(1).reset(236, 280);
//			personagens.get(0).setInimigo(logica.resetarPosicaoInimigos());
//			personagens.get(1).setInimigo(personagens.get(0).getInimigo());
//			logica.setCamadaFundo(Camadas.fase1()[0]);
//			logica.setCamadaColisao(Camadas.fase1()[1]);
//			logica.setCamadaTopo(Camadas.fase1()[2]);
//			tela.getInventario().setStatus("Somando");
//			controlePintura.getThread().stop();
//			controleInimigos.stop();
//		}

	}


	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode()==KeyEvent.VK_ESCAPE) {
//			int op = Mensagem.mensagemJogarNovamente("Deseja encerrar o game?");
//			if(op == 0) {
//				System.exit(0);
//			}
		}

		if (e.getKeyCode()==KeyEvent.VK_W)  cima = true;
		if (e.getKeyCode()==KeyEvent.VK_S) baixo = true;
		if (e.getKeyCode()==KeyEvent.VK_A) esquerda = true;
		if (e.getKeyCode()==KeyEvent.VK_D) direita = true;

		movimento();


	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_W)  cima = false;
		if (e.getKeyCode()==KeyEvent.VK_S) baixo = false;
		if (e.getKeyCode()==KeyEvent.VK_A) esquerda = false;
		if (e.getKeyCode()==KeyEvent.VK_D) direita = false;
	}

	public void movimento() {
		if (esquerda) {
			int xL = personagem.getX();
			int yL = personagem.getY();
			if(xL > 0 ) {
				personagem.setX(xL-2);
				personagem.setY(yL);
				switch(left) {
					case 0:
						personagem.setAparencia(1);
						break;
					case 1:
						personagem.setAparencia(5);
						break;
					case 2:
						personagem.setAparencia(9);
						break;
					case 3:
						personagem.setAparencia(13);
						break;

				}
				if(left == 3) {
					left = 0;
				} else {
					left++;
				}

			}
		}
		if (cima) {
			int xL = personagem.getX();
			int yL = personagem.getY();
			if (yL > 0) {
				personagem.setX(xL);
				personagem.setY(yL-2);
				switch(up) {
					case 0:
						personagem.setAparencia(3);
						break;
					case 1:
						personagem.setAparencia(7);
						break;
					case 2:
						personagem.setAparencia(11);
						break;
					case 3:
						personagem.setAparencia(15);
						break;
				}
				if(up == 3) {
					up = 0;
				} else {
					up++;
				}
			}

		}
		if (direita) {
			int xL = personagem.getX();
			int yL = personagem.getY();
			if (xL < 956) {
				personagem.setX(xL+2);
				personagem.setY(yL);
				switch(right) {
					case 0:
						personagem.setAparencia(2);
						break;
					case 1:
						personagem.setAparencia(6);
						break;
					case 2:
						personagem.setAparencia(10);
						break;
					case 3:
						personagem.setAparencia(14);
						break;

				}
				if(right == 3) {
					right = 0;
				} else {
					right++;
				}
			}
		}
		if (baixo) {
			int xL = personagem.getX();
			int yL = personagem.getY();
			if ( yL < 632 ) {
				personagem.setX(xL);
				personagem.setY(yL+2);
				switch(down) {
					case 0:
						personagem.setAparencia(0);
					case 1:
						personagem.setAparencia(4);
						break;
					case 2:
						personagem.setAparencia(8);
						break;
					case 3:
						personagem.setAparencia(12);
						break;

				}
				if(down == 3) {
					down = 0;
				} else {
					down++;
				}

			}
		}
//		System.out.println("X: " + personagem.getX() + " Y: " + personagem.getY());
	}

}
