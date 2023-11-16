package br.com.ihm.davilnv.view;

import br.com.ihm.davilnv.view.components.GameButton;

import javax.swing.*;
import java.awt.*;

public class Ranking extends TelaGenerica{

//	private JButton voltarButton, jogarButton;
	private GameButton voltarButton;

	public Ranking(String imageBackground) {
		super(imageBackground);

		defaultPanel = new JPanel() {
			protected void paintComponent(Graphics g) {
				g.drawImage(backgroundImage.getImage(), 0, 0, null);
			}
		};
		defaultPanel.setBounds(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		voltarButton = new GameButton("seta-voltar", -400, -864, 52,52);

		add(voltarButton);
		add(defaultPanel);
		
	}

	public JButton getVoltarButton() {
		return voltarButton;
	}
	
}
