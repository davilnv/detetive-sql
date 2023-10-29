package br.com.ihm.davilnv.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.com.ihm.davilnv.model.Camada;
import br.com.ihm.davilnv.model.Camadas;
import br.com.ihm.davilnv.model.Inimigo;
import br.com.ihm.davilnv.model.Inimigos;
import br.com.ihm.davilnv.model.Logica;
import br.com.ihm.davilnv.model.Personagem;
import br.com.ihm.davilnv.view.Ajuda;
import br.com.ihm.davilnv.view.Credito;
import br.com.ihm.davilnv.view.Inicializacao;
import br.com.ihm.davilnv.view.Menu;
import br.com.ihm.davilnv.view.Tela;

public class ControleTelas implements ActionListener{
	private Menu menu;
	private Tela tela;
	private Ajuda ajuda;
	private Inicializacao inicializacao;
	private Credito credito;
	private Personagem personagem, personagem2;
	private Logica logica;
	private ControlePersonagem controlePersonagem;
	private ControleInimigos controleInimigos;
	private Inimigo resultado;
	private ControlePintura controlePintura;
	private String operacao;

	public ControleTelas(Menu menu, Tela tela, Ajuda ajuda, Inicializacao inicializacao, Credito credito, Personagem personagem,
			Personagem personagem2, ControlePersonagem controlePersonagem) {
		this.menu = menu;
		this.tela = tela;
		this.ajuda = ajuda;
		this.inicializacao = inicializacao;
		this.credito = credito;
		this.personagem = personagem;
		this.personagem2 = personagem2;
		this.controlePersonagem = controlePersonagem;
		
		menu.getJogarButton().addActionListener(this);
		menu.getAjudaButton().addActionListener(this);
		menu.getCreditoButton().addActionListener(this);
		menu.getSairButton().addActionListener(this);
		tela.getInventario().getSairButton().addActionListener(this);
		ajuda.getVoltarButton().addActionListener(this);
		inicializacao.getVoltarButton().addActionListener(this);
		inicializacao.getJogarButton().addActionListener(this);
		credito.getVoltarButton().addActionListener(this);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if (menu.getJogarButton() == e.getSource()) {
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
			resultado = personagem.getResultado();
			logica = new Logica(personagem, personagem2, resultado, operacao);
			controleInimigos = new ControleInimigos(personagem.getInimigo(), resultado);
			controlePintura = new ControlePintura(tela, personagem, personagem2, logica, controlePersonagem,
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
			personagem.setLocale(236, 236);
			personagem2.setLocale(236, 280);
			personagem.setVida(100);
			personagem.setPontos(0);
			personagem2.setVida(100);
			personagem2.setPontos(0);
			personagem.setInimigo(logica.resetarPosicaoInimigos());
			personagem2.setInimigo(personagem.getInimigo());
			logica.setCamadaFundo(Camadas.fase1()[0]);
			logica.setCamadaColisao(Camadas.fase1()[1]);
			logica.setCamadaTopo(Camadas.fase1()[2]);
			tela.getInventario().setStatus("Somando");
			controlePintura.getThread().stop();
			controleInimigos.stop();
		}
		if (menu.getAjudaButton() == e.getSource()) {
			ajuda.setVisible(true);
			menu.setVisible(false);
		}
		if (menu.getCreditoButton() == e.getSource()) {
			credito.setVisible(true);
			menu.setVisible(false);
		}
		if (menu.getSairButton() == e.getSource()) {
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
