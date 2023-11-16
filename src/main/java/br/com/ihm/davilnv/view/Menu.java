package br.com.ihm.davilnv.view;

import br.com.ihm.davilnv.view.components.GameButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Menu extends TelaGenerica{
	private List<GameButton> buttonsList;
	
	public Menu(String imageBackground){
		super(imageBackground);

		defaultPanel = new JPanel() {
			protected void paintComponent(Graphics g) {
				g.drawImage(backgroundImage.getImage(), 0, 0, null);
				g.setColor(Color.WHITE);
				g.setFont(new Font("Arial", Font.BOLD, 15));
				g.drawString("Versão: 1.0 - Criado por Davi Lima", 20, DEFAULT_HEIGHT - 20);
			}
		};
		defaultPanel.setBounds(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);

		buttonsList = new ArrayList<>();
		buttonsList.add(new GameButton("jogar", 0, 0, 0,0));
		buttonsList.add(new GameButton("ranking", 80, 0, 0,0));
		buttonsList.add(new GameButton("tutorial", 160, 0, 0,0));
		buttonsList.add(new GameButton("creditos", 240, 0, 0,0));
		buttonsList.add(new GameButton("sair", 320, 0, 0,0));

		for (GameButton button : buttonsList) {
			add(button);
		}
		add(defaultPanel);
		
		setVisible(true);
	}

	public JButton getButtonByKey(String key) {
		for (GameButton button : buttonsList) {
			if (button.getKey().equals(key)) {
				return button;
			}
		}
		return null;
	}

	public List<GameButton> getButtonsList() {
		return buttonsList;
	}
}
