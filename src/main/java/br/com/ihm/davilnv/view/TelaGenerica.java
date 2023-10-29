package br.com.ihm.davilnv.view;

import javax.swing.JFrame;

public abstract class TelaGenerica extends JFrame{
	public static final int LARGURA = 640;
	public static final int ALTURA = 513;
	
	public TelaGenerica() {
		setSize(LARGURA, ALTURA);
		setUndecorated(true);
		setResizable(false);
		setLocationRelativeTo(null);
	}
}
