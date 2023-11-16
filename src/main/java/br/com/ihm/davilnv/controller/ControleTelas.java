package br.com.ihm.davilnv.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import br.com.ihm.davilnv.model.*;
import br.com.ihm.davilnv.view.*;
import br.com.ihm.davilnv.view.components.GameButton;

public class ControleTelas implements ActionListener{
	private Menu menu;
	private Ranking ranking;
	private Tela tela;
	private Tutorial tutorial;
	private Inicializacao inicializacao;
	private Credito credito;
	private List<Personagem> personagens;
	private List<InventoryInfo> inventoryInfos;
	private Logica logica;
	private ControlePersonagem controlePersonagem;
	private ControleInimigos controleInimigos;
	private Inimigo resultado;
	private ControlePintura controlePintura;
	private String operacao;

	public ControleTelas() throws IOException {
//		personagens = new ArrayList<>();
//		Inimigo resultado = new Inimigo(30, 176, "");
//		Personagem personagem = new Personagem(Inimigos.iniciarInimigos(), resultado, 0, 128, 96, 4, 3, 236, 236, "/res/personagem.png");
//		personagens.add(personagem);
//		tela = new Tela(personagens);
		menu = new Menu("/assets/images/background/background-frames-menu.png");
		inicializacao = new Inicializacao("/assets/images/background/background-frames-inicio.png");
		ranking = new Ranking("/assets/images/background/background-frames-ranking.png");
		tutorial = new Tutorial("/assets/images/background/background-frames-tutorial.png");
		credito = new Credito("/assets/images/background/background-frames-creditos.png");
//
//		controlePersonagem = new ControlePersonagem(personagens);
//
		for (GameButton button : menu.getButtonsList()) {
			button.addActionListener(this);
		}
//		tela.getInventario().getSairButton().addActionListener(this);
		ranking.getVoltarButton().addActionListener(this);
		tutorial.getVoltarButton().addActionListener(this);
		inicializacao.getVoltarButton().addActionListener(this);
		inicializacao.getJogarButton().addActionListener(this);
		credito.getVoltarButton().addActionListener(this);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// Menu Buttons
		if (menu.getButtonByKey("jogar") == e.getSource()) {
			menu.setVisible(false);
			inicializacao.setVisible(true);
		}
		if (menu.getButtonByKey("ranking") == e.getSource()) {
			menu.setVisible(false);
			ranking.setVisible(true);
		}
		if (menu.getButtonByKey("tutorial") == e.getSource()) {
			menu.setVisible(false);
			tutorial.setVisible(true);
		}
		if (menu.getButtonByKey("credito") == e.getSource()) {
			menu.setVisible(false);
			credito.setVisible(true);
		}
		if (menu.getButtonByKey("sair") == e.getSource()) {
			System.exit(0);
		}
		// Voltar
		if (inicializacao.getVoltarButton() == e.getSource()) {
			inicializacao.setVisible(false);
			menu.setVisible(true);
		}
		if (ranking.getVoltarButton() == e.getSource()) {
			ranking.setVisible(false);
			menu.setVisible(true);
		}
		if (tutorial.getVoltarButton() == e.getSource()) {
			tutorial.setVisible(false);
			menu.setVisible(true);
		}
		if (credito.getVoltarButton() == e.getSource()) {
			credito.setVisible(false);
			menu.setVisible(true);
		}

		// Game
		if (inicializacao.getJogarButton() == e.getSource()) {
			boolean multplayer = false;
			resultado = personagens.get(0).getResultado();
			logica = new Logica(personagens, resultado, operacao);
			controleInimigos = new ControleInimigos(personagens.get(0).getInimigo(), resultado);
			controlePintura = new ControlePintura(tela, personagens, logica, controlePersonagem,
					controleInimigos,resultado);
			controlePintura.setMultplayer(multplayer);
			tela.getInventario().setMultplayer(multplayer);
			menu.setVisible(false);
			tela.setVisible(true);
			tela.requestFocus();
		}
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
