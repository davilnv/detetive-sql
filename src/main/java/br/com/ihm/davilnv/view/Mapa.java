package br.com.ihm.davilnv.view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Mapa extends JPanel{
	private BufferedImage fundo;
	private Graphics graphicsMapa;
	
	public Mapa() {
		setSize(512, 512);
		setLocation(0, 0);
		
		fundo = new BufferedImage(Tela.DEFAULT_WIDTH, Tela.DEFAULT_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
		graphicsMapa = fundo.getGraphics();
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		g.drawImage(fundo, 0, 0, null);
		
	}

	public Graphics getGraphicsMapa() {
		return graphicsMapa;
	}
}
