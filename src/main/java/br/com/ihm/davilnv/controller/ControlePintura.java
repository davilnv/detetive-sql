package br.com.ihm.davilnv.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

import br.com.ihm.davilnv.view.MainFrame;

import br.com.ihm.davilnv.model.Camada;
import br.com.ihm.davilnv.model.Logica;
import br.com.ihm.davilnv.model.Inimigo;
import br.com.ihm.davilnv.model.Personagem;

public class ControlePintura implements Runnable{

	private MainFrame mainFrame;
	private List<Personagem> personagens;
	private Logica logica;
	private ControlePersonagem controlePersonagem;
	private Graphics g;
	private List<Camada> camadas;
	public static List<Rectangle> colisao;
	private Thread thread;
	private boolean multplayer;
	
	public ControlePintura(MainFrame mainFrame, List<Personagem> personagens, Logica logica,
			ControlePersonagem controlePersonagem) {
		this.mainFrame = mainFrame;
		this.personagens = personagens;
		this.logica = logica;
		this.controlePersonagem = controlePersonagem;
		
		camadas = logica.getCamadas();

		for (Camada camada : camadas) {
			if (camada.getKey().equals("colision"))
				colisao = camada.montarColisao();
		}
		
		this.g = mainFrame.getPanelByKey("map").getGraphics();
		
		mainFrame.addKeyListener(controlePersonagem);
		
		thread = new Thread(this);
		
		thread.start();
	}
	
	private void atualizarCamadas() {
		camadas = logica.getCamadas();
		for (Camada camada : camadas) {
			if (camada.getKey().equals("colision"))
				colisao = camada.montarColisao();
		}
	}
	
	public void draw() {
		for (Camada camada : camadas) {
			if (camada.getKey().equals("floor") || camada.getKey().equals("colision"))
				g.drawImage(camada.camada, 0, 0, null);
		}
		g.drawImage(personagens.get(0).getSprites()[personagens.get(0).getAparencia()], personagens.get(0).getX(), personagens.get(0).getY(), null);
		if (multplayer)
			g.drawImage(personagens.get(1).getSprites()[personagens.get(1).getAparencia()], personagens.get(1).getX(), personagens.get(1).getY(), null);
		else
			g.drawImage(personagens.get(1).getSprites()[personagens.get(1).getAparencia()], 1000, 1000, null);
		for (Camada camada : camadas) {
			if (!camada.getKey().equals("colision") && !camada.getKey().equals("floor"))
				g.drawImage(camada.camada, 0, 0, null);
		}
		for (Camada camada : camadas) {
			if (!camada.getKey().equals("floor") || !camada.getKey().equals("colision"))
				g.drawImage(camada.camada, 0, 0, null);
		}
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		
	}
	
	public void update(){
		atualizarCamadas();
		for (Camada camada : camadas) {
			camada.montarMapa();
		}
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
//			System.out.println("rodando");
			mainFrame.getPanelByKey("map").repaint();
			update();
			draw();
//			tela.getInventario().repaint();
			logica.iniciarFase();
			morrer();
			ganhou();
		}
		
	}

	public Thread getThread() {
		return thread;
	}

	public boolean isMultplayer() {
		return multplayer;
	}

	public void setMultplayer(boolean multplayer) {
		this.multplayer = multplayer;
	}
	
}
