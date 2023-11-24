package br.com.ihm.davilnv.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.ihm.davilnv.model.*;
import br.com.ihm.davilnv.view.*;
import br.com.ihm.davilnv.view.components.GameButton;

public class FrameController implements ActionListener{
	MainFrame mainFrame;
	private List<Personagem> personagens;
	private List<InventoryInfo> inventoryInfos;
	private Logica logica;
	private ControlePersonagem controlePersonagem;
	private ControlePintura controlePintura;

	public FrameController() throws IOException {
		mainFrame = new MainFrame();

		personagens = new ArrayList<>();
		Personagem personagem = new Personagem(0, 32, 64, 4, 3, 236, 236, "/assets/images/sprite/sprite-detective_universal.png");
		Personagem personagem2 = new Personagem(0, 32, 64, 4, 3, 236, 236, "/assets/images/sprite/sprite-detective_universal.png"); // TODO: gambiarra, remover depois
//
		personagens.add(personagem);
		personagens.add(personagem2);

		controlePersonagem = new ControlePersonagem(personagens);

		for (GameButton button : mainFrame.buttons) {
			button.addActionListener(this);
		}
	}



	@Override
	public void actionPerformed(ActionEvent e) {

//		// Game
		if (mainFrame.getButtonByKey("jogar") == e.getSource() && mainFrame.getCurrentPanel().getKey().equals("start")) {
			boolean multplayer = false;
			logica = new Logica(personagens);
			controlePintura = new ControlePintura(mainFrame, personagens, logica, controlePersonagem);
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

}
