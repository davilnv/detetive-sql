package br.com.ihm.davilnv.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.List;

import org.w3c.dom.events.MouseEvent;

import br.com.ihm.davilnv.model.Camada;
import br.com.ihm.davilnv.model.Camadas;
import br.com.ihm.davilnv.model.Logica;
import br.com.ihm.davilnv.model.Inimigo;
import br.com.ihm.davilnv.model.NumerosAleatorios;
import br.com.ihm.davilnv.model.Personagem;
import br.com.ihm.davilnv.view.Tela;

public class ControlePintura implements Runnable{

	private Tela tela;
	private List<Personagem> personagens;
	private Logica logica;
	private ControlePersonagem controlePersonagem;
	private ControleInimigos controleInimigos;
	private Inimigo resultado;
	private Graphics g;
	private Camada camadaFundo, camadaColisao, camadaTopo;
	public static List<Rectangle> colisao;
	private Thread thread;
	private boolean multplayer;
	
	public ControlePintura(Tela tela, List<Personagem> personagens, Logica logica,
			ControlePersonagem controlePersonagem, ControleInimigos controleInimigos, Inimigo resultado) {
		this.tela = tela;
		this.personagens = personagens;
		this.logica = logica;
		this.controlePersonagem = controlePersonagem;
		this.controleInimigos = controleInimigos;
		this.resultado = resultado;
		
		this.camadaFundo = logica.getCamadaFundo();
		this.camadaColisao = logica.getCamadaColisao();
		this.camadaTopo = logica.getCamadaTopo();

		colisao = camadaColisao.montarColisao();
		
		this.g = tela.getMapa().getGraphicsMapa();
		
		tela.addKeyListener(controlePersonagem);
		
		thread = new Thread(this);
		
		thread.start();
	}
	
	private void atualizarCamadas() {
		this.camadaFundo = logica.getCamadaFundo();
		this.camadaColisao = logica.getCamadaColisao();
		this.camadaTopo = logica.getCamadaTopo();
		colisao = camadaColisao.montarColisao();
	}
	
	public void draw() {
		g.drawImage(camadaFundo.camada, 0, 0, null);
		g.drawImage(camadaColisao.camada, 0, 0, null);

		for (Inimigo enemy : personagens.get(0).getInimigo()) {
			g.drawString(enemy.getAparencia(), enemy.getX()+5, enemy.getY()+25);
		}
		g.drawString(resultado.getAparencia(), resultado.getX()+5, resultado.getY()+25);
		g.drawImage(personagens.get(0).getSprites()[personagens.get(0).getAparencia()], personagens.get(0).getX(), personagens.get(0).getY(), null);
		if (multplayer)
			g.drawImage(personagens.get(1).getSprites()[personagens.get(1).getAparencia()], personagens.get(1).getX(), personagens.get(1).getY(), null);
		else
			g.drawImage(personagens.get(1).getSprites()[personagens.get(1).getAparencia()], 1000, 1000, null);
		g.drawImage(camadaTopo.camada, 0, 0, null);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		switch (logica.getOperacao()) {
		case "soma":
			g.drawString(logica.getNum1() + " + " + logica.getNum2() + " = ", 160, 52);
			break;
		case "subtracao":
			g.drawString(logica.getNum1() + " - " + logica.getNum2() + " = ", 160, 52);
			break;
		case "multiplicacao":
			g.drawString(logica.getNum1() + " X " + logica.getNum2() + " = ", 160, 52);
			break;
		case "divisao":
			g.drawString(logica.getNum1() + " / " + logica.getNum2() + " = ", 160, 52);
			break;
		}
		
	}
	
	public void update(){
		atualizarCamadas();
		camadaFundo.montarMapa();
		camadaColisao.montarMapa();
		camadaTopo.montarMapa();
	}
	
	public void morrer() {
		if (personagens.get(0).getVida() == 0 || personagens.get(1).getVida() == 0) {
			thread.stop();
		}
	}
	
	public void ganhou() {
		if (logica.isGanhou()) {
			thread.stop();
		}
	}
	
	@Override
	public void run() {
		
		while(true) {
			tela.getMapa().repaint();
			update();
			draw();
			tela.getInventario().repaint();
			logica.iniciarFase();
			morrer();
			ganhou();
		}
		
	}

	public Thread getThread() {
		return thread;
	}

	public ControleInimigos getControleInimigos() {
		return controleInimigos;
	}

	public void setControleInimigos(ControleInimigos controleInimigos) {
		this.controleInimigos = controleInimigos;
	}

	public boolean isMultplayer() {
		return multplayer;
	}

	public void setMultplayer(boolean multplayer) {
		this.multplayer = multplayer;
	}
	
}
