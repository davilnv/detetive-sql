package br.com.ihm.davilnv.view;

import javax.swing.JFrame;

public abstract class TelaGenerica extends JFrame{
	public static final int DEFAULT_WIDTH = 1920;
	public static final int DEFAULT_HEIGHT = 1080;

	public static final int DEFAULT_WIDTH_BUTTON = 300;
	public static final int DEFAULT_HEIGHT_BUTTON = 60;

	public static final String DEFAULT_BUTTONS_PATH = "/assets/buttons/";
	
	public TelaGenerica() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setUndecorated(true);
		setResizable(false);
		setLocationRelativeTo(null);
	}
}
