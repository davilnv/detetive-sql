package br.com.ihm.davilnv.view;

import br.com.ihm.davilnv.view.components.GameButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Inicializacao extends TelaGenerica{
	
//	private JButton voltarButton, jogarButton;
	private GameButton voltarButton, jogarButton;
	
	public Inicializacao(String imageBackground) {
		super(imageBackground);

		defaultPanel = new JPanel() {
			protected void paintComponent(Graphics g) {
				g.drawImage(backgroundImage.getImage(), 0, 0, null);
			}
		};
		defaultPanel.setBounds(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		voltarButton = new GameButton("seta-voltar", -400, -864, 52,52);
		jogarButton = new GameButton("jogar", 500-DEFAULT_HEIGHT_BUTTON, 0, 0,0);

		add(voltarButton);
		add(jogarButton);
		add(defaultPanel);
		
	}

	public JButton getVoltarButton() {
		return voltarButton;
	}

	public JButton getJogarButton() {
		return jogarButton;
	}
	
}
