package br.com.ihm.davilnv.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.ihm.davilnv.model.*;
import br.com.ihm.davilnv.view.Ajuda;
import br.com.ihm.davilnv.view.Credito;
import br.com.ihm.davilnv.view.Inicializacao;
import br.com.ihm.davilnv.view.Menu;
import br.com.ihm.davilnv.view.Tela;
import br.com.ihm.davilnv.view.components.GameButton;

public class ControleTelas implements ActionListener{
	private Menu menu;
	private Tela tela;
	private Ajuda ajuda;
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
		menu = new Menu("/assets/background/menu-game.png");
//		ajuda = new Ajuda();
//		inicializacao = new Inicializacao();
//		credito = new Credito();
//
//		controlePersonagem = new ControlePersonagem(personagens);
//
//
		for (GameButton button : menu.getButtonsList()) {
			button.addActionListener(this);
		}
//		tela.getInventario().getSairButton().addActionListener(this);
//		ajuda.getVoltarButton().addActionListener(this);
//		inicializacao.getVoltarButton().addActionListener(this);
//		inicializacao.getJogarButton().addActionListener(this);
//		credito.getVoltarButton().addActionListener(this);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if (menu.getButtonByKey("jogar") == e.getSource()) {
			menu.setVisible(false);
			inicializacao.setVisible(true);

		}
		if (inicializacao.getVoltarButton() == e.getSource()) {
			inicializacao.setVisible(false);
			menu.setVisible(true);
		}
		if (inicializacao.getJogarButton() == e.getSource()) {
			boolean multplayer = false;
			if (inicializacao.getSingleButton().isSelected()) {
				multplayer = false;
			}
			if (inicializacao.getMultButton().isSelected()) {
				multplayer = true;
			}
			verificarOperacao();
			resultado = personagens.get(0).getResultado();
			logica = new Logica(personagens, resultado, operacao);
			controleInimigos = new ControleInimigos(personagens.get(0).getInimigo(), resultado);
			controlePintura = new ControlePintura(tela, personagens, logica, controlePersonagem,
					controleInimigos,resultado);
			controlePintura.setMultplayer(multplayer);
			tela.getInventario().setMultplayer(multplayer);
			if (inicializacao.getFacilButton().isSelected()) {
				controlePintura.getControleInimigos().setVelocidade(15);
			}
			if (inicializacao.getMedioButton().isSelected()) {
				controlePintura.getControleInimigos().setVelocidade(10);
			}
			if (inicializacao.getDificilButton().isSelected()) {
				controlePintura.getControleInimigos().setVelocidade(6);
			}
			menu.setVisible(false);
			tela.setVisible(true);
			tela.requestFocus();
		}
		if (tela.getInventario().getSairButton() == e.getSource()) {
			tela.setVisible(false);
			menu.setVisible(true);
			personagens.get(0).reset(236, 236);
			personagens.get(1).reset(236, 280);
			personagens.get(0).setInimigo(logica.resetarPosicaoInimigos());
			personagens.get(1).setInimigo(personagens.get(0).getInimigo());
			logica.setCamadaFundo(Camadas.fase1()[0]);
			logica.setCamadaColisao(Camadas.fase1()[1]);
			logica.setCamadaTopo(Camadas.fase1()[2]);
			tela.getInventario().setStatus("Somando");
			controlePintura.getThread().stop();
			controleInimigos.stop();
		}
		if (menu.getButtonByKey("tutorial") == e.getSource()) {
			ajuda.setVisible(true);
			menu.setVisible(false);
		}
		if (menu.getButtonByKey("credito") == e.getSource()) {
			credito.setVisible(true);
			menu.setVisible(false);
		}
		if (menu.getButtonByKey("sair") == e.getSource()) {
			System.exit(0);
		}
		if (ajuda.getVoltarButton() == e.getSource()) {
			ajuda.setVisible(false);
			menu.setVisible(true);
		}
		if (credito.getVoltarButton() == e.getSource()) {
			credito.setVisible(false);
			menu.setVisible(true);
		}
	}
	
	public void verificarOperacao() {
		if(inicializacao.getSomaButton().isSelected()) {
			operacao = "soma";
		}
		if(inicializacao.getSubButton().isSelected()) {
			operacao = "subtracao";
		}
		if(inicializacao.getMultiplicaButton().isSelected()) {
			operacao = "multiplicacao";
		}
		if(inicializacao.getDivButton().isSelected()) {
			operacao = "divisao";
		}
	}
}
